package com.example.sweetflowershop.ui.adapter.MessageAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.databinding.ContactShopBinding
import com.example.sweetflowershop.databinding.ContactUserBinding

class UserMessageViewHolder(private val binding: ContactUserBinding) : RecyclerView.ViewHolder(binding.root) {
    val senderTextView: TextView = binding.textviewUserName
    val contentTextView: TextView = binding.textviewContent
}