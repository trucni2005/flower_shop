package com.example.sweetflowershop.network.apiService

import android.util.Log
import okhttp3.*

class WebSocketClient(private val url: Request, private val onMessageReceived: (String) -> Unit) {

    private val TAG = "WebSocketClient"
    private val client = OkHttpClient()
    private lateinit var webSocket: WebSocket

    fun connect() {
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, WebSocketListener())
        Log.d(TAG, "Connecting to WebSocket...")
    }

    fun disconnect() {
        webSocket.close(1000, null)
        Log.d(TAG, "Disconnecting from WebSocket...")
    }

    private inner class WebSocketListener : okhttp3.WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            // Đã kết nối thành công
            Log.d(TAG, "WebSocket connection opened successfully.")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            // Nhận thông báo từ server
            onMessageReceived(text)
            Log.d(TAG, "Received message from server: $text")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            // Đóng kết nối
            Log.d(TAG, "WebSocket connection closed. Code: $code, Reason: $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            // Xử lý lỗi
            Log.e(TAG, "WebSocket connection failure.", t)
        }
    }
}
