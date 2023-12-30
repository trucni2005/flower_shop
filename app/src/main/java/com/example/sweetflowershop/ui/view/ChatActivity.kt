package com.example.sweetflowershop.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.data.model.message.Message
import com.example.sweetflowershop.databinding.ActivityConversationBinding
import com.example.sweetflowershop.ui.adapter.MessageAdapter.MessageAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatActivity : AppCompatActivity() {

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var binding: ActivityConversationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val messages = mutableListOf(
            Message("user", "12:30","Hello!"),
            Message("admin", "13:30","Hi there! How can I help?")
        )

        messageAdapter = MessageAdapter(messages)
        binding.rcvMessage.adapter = messageAdapter
        binding.rcvMessage.layoutManager = LinearLayoutManager(this)

        messageAdapter.notifyDataSetChanged()
        binding.rcvMessage.scrollToPosition(messages.size - 1)
//        binding.btnSend.setOnClickListener {
//            sendMessage()
//        }
    }

//    private fun sendMessage() {
//        val message = binding.editTextMessage.text.toString().trim()
//        if (message.isNotEmpty()) {
//            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
//            val newMessage = Message("user", currentTime, message)
//            messageAdapter.addMessage(newMessage)
//            messageAdapter.notifyDataSetChanged()
//
//            binding.editTextMessage.text.clear()
//            binding.rcvMessage.scrollToPosition(messageAdapter.itemCount - 1)
//        }
//    }
}
