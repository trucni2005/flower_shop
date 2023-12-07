package com.example.sweetflowershop.ui.viewmodel.notification

import android.app.Application
import com.example.sweetflowershop.ui.view.order.OrderSuccessFulActivity

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NotificationViewModel(application: Application) : AndroidViewModel(application) {

    private val _notificationData = MutableLiveData<Pair<String, String>>()

    fun processNotification(title: String?, body: String?) {
        if (body != null) {
            // Log thông tin body
            android.util.Log.d("NotificationViewModel", "Nhận được body: $body")

            if (body == "payment_success") {
                // Log thông tin sự kiện payment_success
                android.util.Log.d("NotificationViewModel", "Xử lý sự kiện payment_success")

                // Cập nhật LiveData với thông tin notification
                _notificationData.postValue(Pair(title ?: "payment_success", body))
            }
        }
    }
}
