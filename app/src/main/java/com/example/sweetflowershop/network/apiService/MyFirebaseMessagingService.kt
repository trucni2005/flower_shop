package com.example.sweetflowershop.network.apiService;
import android.content.Intent
import android.util.Log
import com.example.sweetflowershop.data.model.notification.NotificationData
import com.example.sweetflowershop.ui.view.order.CheckoutActivity
import com.example.sweetflowershop.ui.view.order.OrderSuccessFulActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Log thông điệp nhận được
        Log.d("MyFirebaseMessaging", "Received message: $remoteMessage")

        // Lấy dữ liệu từ tin nhắn
        val title = remoteMessage.data["title"]
        val body = remoteMessage.data["body"]
        val data = remoteMessage.data["data"]

        Log.d("MyFirebaseMessaging", "Title: $title")
        Log.d("MyFirebaseMessaging", "Body: $body")
        Log.d("MyFirebaseMessaging", "Data: $data")

        // Xử lý thông điệp
        when (title) {
            "PAYMENT" -> {
                if (body == "payment_success") {
                    Log.d("MyFirebaseMessaging", "Payment success message received")
                    GlobalScope.launch {
                        val intent = Intent(applicationContext, OrderSuccessFulActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                } else if (body == "payment_failed") {
                    Log.d("MyFirebaseMessaging", "Payment failure message received")
                    GlobalScope.launch {
                        val intent = Intent(applicationContext, CheckoutActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            }
            // Thêm các trường hợp khác nếu cần thiết
            else -> {
                val notificationData = Gson().fromJson(data, NotificationData::class.java)
                Log.d("MyFirebaseMessaging", "Notification: $notificationData")
                Log.d("MyFirebaseMessaging", "Unknown title: $title")
            }
        }
    }
}