package com.example.sweetflowershop.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.ui.view.main.MainActivity
import com.example.sweetflowershop.data.repository.CartRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.math.BigDecimal
import java.math.RoundingMode

class CartViewModel : ViewModel() {

    private val cartApiServices = CartRepository()

    private val _cartItemsLiveData = MutableLiveData<List<CartItem>>()

    val cartItemsLiveData: LiveData<List<CartItem>>
        get() = _cartItemsLiveData

    private val _totalBillLiveData = MutableLiveData<Double>()
    val totalBillLiveData: LiveData<Double>
        get() = _totalBillLiveData

    fun fetchCartItems(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)
        Log.d("1", token.toString())
        if (!token.isNullOrEmpty()) {
            cartApiServices.getCartItems(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CartItem>>() {
                    override fun onSuccess(cartItems: List<CartItem>) {
                        _cartItemsLiveData.value = cartItems
                        for (cartItem in cartItems) {
                            Log.d("Test", "CartItem: $cartItem")
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Test", "Error: ${e.message}")
                    }
                })

        } else {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("shouldLaunchLoginActivity", true)
            context.startActivity(intent)
        }
    }

    private fun calculateTotalBill(cartItems: List<CartItem>) {
        var totalBill = 0.0
        for (cartItem in cartItems) {
            totalBill += cartItem.product.price.toDouble() * cartItem.quantity
        }
        _totalBillLiveData.value = BigDecimal(totalBill).setScale(2, RoundingMode.HALF_EVEN).toDouble()

    }

    fun getTotalBill(): LiveData<Double> {
        return totalBillLiveData
    }

    fun setTotalBill(cartItems: List<CartItem>) {
        calculateTotalBill(cartItems)
    }
}
