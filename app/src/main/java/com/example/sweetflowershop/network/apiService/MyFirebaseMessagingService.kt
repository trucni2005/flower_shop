package com.example.sweetflowershop.network.apiService

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.sweetflowershop.ui.view.order.CheckoutActivity
import com.example.sweetflowershop.ui.viewmodel.notification.NotificationViewModel
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.sweetflowershop.ui.view.order.OrderSuccessFulActivity
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val messageBody = remoteMessage.data["content"]
        if (messageBody != null) {
            Log.d("messageBody", messageBody)

            if (messageBody == "payment_success")
            GlobalScope.launch {
                val intent = Intent(applicationContext, OrderSuccessFulActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            if (messageBody == "payment_fail")
                GlobalScope.launch {
                    val intent = Intent(applicationContext, CheckoutActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
        }
    }
}