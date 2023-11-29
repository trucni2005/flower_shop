package com.example.sweetflowershop.ui.view.order

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.databinding.WaitingForPaymentBinding
import com.example.sweetflowershop.network.apiService.WebSocketClient
import okhttp3.Request

class WaitingForPaymentActivity : AppCompatActivity() {
    private lateinit var binding: WaitingForPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WaitingForPaymentBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val sharedPreferences = this.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)

        // Tạo yêu cầu WebSocket với mã xác thực
        val request = Request.Builder()
            .url("ws://localhost:80/ws")
            .addHeader("token", "$token")
            .build()

        val webSocketClient = WebSocketClient(request) { message ->
            Log.d("WebSocketClient", "Received message from server: $message")

            if (message == "Success") {
                runOnUiThread {
                    Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show()
                }
            }

            if (message == "Fail") {
                runOnUiThread {
                    Toast.makeText(this, "Payment failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Kết nối đến WebSocket
        webSocketClient.connect()
    }
}