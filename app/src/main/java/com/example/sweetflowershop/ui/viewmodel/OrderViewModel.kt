package com.example.sweetflowershop.ui.viewmodel

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.order.Order
import com.example.sweetflowershop.data.model.order.OrderToSend
import com.example.sweetflowershop.data.model.order.Payment
import com.example.sweetflowershop.data.repository.OrderAPIService
import com.example.sweetflowershop.ui.view.main.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class OrderViewModel : ViewModel() {
    private val orderApiService = OrderAPIService()

    private val _orderListLiveData = MutableLiveData<List<Order>>()
    val orderListLiveData: LiveData<List<Order>> = _orderListLiveData

    private val _orderLiveData = MutableLiveData<Order>()
    val orderLiveData: LiveData<Order> = _orderLiveData

    fun createOrder(
        context: Context,
        addressId: Int,
        voucherId: Long?,
        paymentOnline: Boolean,
        note: String?,
        onSuccess: (Order) -> Unit,
        onError: (String) -> Unit
    ) {
        Log.d("AddressId", addressId.toString())
        val token = context.getAuthorizationToken()

        if (!token.isNullOrEmpty()) {
            orderApiService.createOrder(token, OrderToSend(addressId, voucherId, paymentOnline, note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Order>() {
                    override fun onSuccess(order: Order) {
                        _orderLiveData.value = order
                        Log.d("createOrder", order.toString())
                        onSuccess(order)
                    }

                    override fun onError(e: Throwable) {
                        onError(e.message ?: "Unknown error")
                        Log.d("OrderViewModel", "Error creating order: ${e.message}")
                    }
                })
        } else {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("shouldLaunchLoginActivity", true)
            context.startActivity(intent)
        }
    }

    fun confirmOrder(
        context: Context,
        addressId: Int,
        voucherId: Long?,
        paymentOnline: Boolean,
        note: String?,
        onSuccess: (Order) -> Unit,
        onError: (String) -> Unit
    ) {
        Log.d("AddressId", addressId.toString())
        val token = context.getAuthorizationToken()


        if (!token.isNullOrEmpty()) {
            var temp = voucherId;
            if (temp?.toInt() == -1)
            {
                temp = null
            }
            orderApiService.confirmOrder(token, OrderToSend(addressId, temp, paymentOnline, note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Payment>() {
                    override fun onSuccess(payment: Payment) {
                        if (paymentOnline == true){
                        Log.d("SUCCESS", payment.urlQR)
                        Log.d("SUCCESS", payment.order.toString())
                        Log.d("SUCCESS", payment.methodPayment)
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(payment.urlQR))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Thêm cờ để mở trình duyệt trong một task mới

                        try {
                            ContextCompat.startActivity(context, intent, null)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, "No web browser found", Toast.LENGTH_SHORT).show()
                        }
                        }
                    }

                    override fun onError(e: Throwable) {
                        onError(e.message ?: "Unknown error")
                        Log.d("OrderViewModel", "Error creating order: ${e.message}")
                    }
                })
        } else {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("shouldLaunchLoginActivity", true)
            context.startActivity(intent)
        }
    }

    // Extension function to get authorization token from SharedPreferences
    private fun Context.getAuthorizationToken(): String? {
        return getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            .getString("Authorization", null)
    }
}