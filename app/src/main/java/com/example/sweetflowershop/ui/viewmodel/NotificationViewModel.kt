package com.example.sweetflowershop.ui.viewmodel

import android.app.Application
import com.example.sweetflowershop.ui.view.order.OrderSuccessFulActivity

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.notification.NotificationData
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repository.NotificationRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class NotificationViewModel : ViewModel() {
    private val apiServices = NotificationRepository()
    private val _notificationList = MutableLiveData<List<NotificationData>>()
    val notificationList: LiveData<List<NotificationData>> get() = _notificationList
    fun updateNotificationList(newReviews: List<NotificationData>) {
        _notificationList.value = newReviews
    }

    fun fetchNotificationList(context: Context) {
        Log.d("NotificationViewModel", "Fetching notification list")

        val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)
        Log.d("NotificationViewModel", "Authorization token: $token")

        if (token != null) {
            apiServices.getAllNotification(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<NotificationData>>() {
                    override fun onSuccess(notifications: List<NotificationData>) {
                        Log.d("NotificationViewModel", "API call success. Received ${notifications.size} notifications.")
                        _notificationList.value = notifications
                    }

                    override fun onError(e: Throwable) {
                        Log.e("NotificationViewModel", "API call error: ${e.message}")
                        // Handle error if needed
                    }
                })
        } else {
            Log.e("NotificationViewModel", "Token is null. Cannot make API call.")
        }
    }

}