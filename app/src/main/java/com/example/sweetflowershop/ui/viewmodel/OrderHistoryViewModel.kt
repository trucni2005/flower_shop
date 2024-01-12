package com.example.sweetflowershop.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.order.OrderHistory
import com.example.sweetflowershop.data.repository.OrderRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class OrderHistoryViewModel : ViewModel() {
    private val apiServices = OrderRepository() // Thay thế bằng API service của bạn
    private val _orderHistoryLiveData = MutableLiveData<List<OrderHistory>>()

    val orderHistoryLiveData: LiveData<List<OrderHistory>> = _orderHistoryLiveData

    fun fetchOrderHistory(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)
        Log.d("1", token.toString())

        if (token != null) {
            apiServices.getOrderHistory(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<OrderHistory>>() {
                    override fun onSuccess(orderHistoryList: List<OrderHistory>) {
                        _orderHistoryLiveData.value = orderHistoryList
                        Log.d("fetchOrderHistory", orderHistoryList.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e("fetchOrderHistory", "Error fetching order history", e)
                    }
                })
        }
    }

    override fun onCleared() {
        // Gọi khi ViewModel được hủy
        super.onCleared()
        // Huỷ bất kỳ nguồn dữ liệu không đồng bộ nếu cần
    }
}
