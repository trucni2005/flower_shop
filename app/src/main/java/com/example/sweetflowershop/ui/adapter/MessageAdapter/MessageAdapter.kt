package com.example.sweetflowershop.ui.adapter.MessageAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.message.Message
import com.example.sweetflowershop.databinding.ContactShopBinding
import com.example.sweetflowershop.databinding.ContactUserBinding

class MessageAdapter(private val messages: List<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            USER_MESSAGE_TYPE -> {
                val binding = ContactUserBinding.inflate(inflater, parent, false)
                Log.d("MessageAdapter", "Creating UserMessageViewHolder")
                UserMessageViewHolder(binding)
            }
            ADMIN_MESSAGE_TYPE -> {
                val binding = ContactShopBinding.inflate(inflater, parent, false)
                Log.d("MessageAdapter", "Creating AdminMessageViewHolder")
                AdminMessageViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messages[position]

        when (holder) {
            is UserMessageViewHolder -> {
                holder.contentTextView.text = currentMessage.content
                holder.senderTextView.text = currentMessage.sender
                Log.d("MessageAdapter", "Binding UserMessageViewHolder at position $position")
            }
            is AdminMessageViewHolder -> {
                holder.contentTextView.text = currentMessage.content
                holder.senderTextView.text = currentMessage.sender
                Log.d("MessageAdapter", "Binding AdminMessageViewHolder at position $position")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val viewType = when (messages[position].sender) {
            "user" -> USER_MESSAGE_TYPE
            "admin" -> ADMIN_MESSAGE_TYPE
            else -> throw IllegalArgumentException("Invalid message sender")
        }
        Log.d("MessageAdapter", "Item at position $position has view type $viewType")
        return viewType
    }

    override fun getItemCount() = messages.size

    companion object {
        private const val USER_MESSAGE_TYPE = 1
        private const val ADMIN_MESSAGE_TYPE = 2
    }

//    fun addMessage(message: Message) {
//        messages.add(message)
//    }
}
