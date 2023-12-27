package com.example.sweetflowershop.ui.adapter.MessageAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.databinding.ContactShopBinding

class AdminMessageViewHolder(private val binding: ContactShopBinding) : RecyclerView.ViewHolder(binding.root) {
    val senderTextView: TextView = binding.textviewUserName
    val contentTextView: TextView = binding.textviewUserContent
}