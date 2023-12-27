package com.example.sweetflowershop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.notification.NotificationData
import com.example.sweetflowershop.databinding.ItemNotificationBinding
import com.example.sweetflowershop.ui.viewmodel.NotificationViewModel

class NotificationAdapter(private val viewModel: NotificationViewModel) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private var notifications: List<NotificationData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding: ItemNotificationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_notification,
            parent,
            false
        )
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    fun submitList(newNotifications: List<NotificationData>) {
        notifications = newNotifications
        notifyDataSetChanged()
    }

    inner class NotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: NotificationData) {
            binding.item = notification
            binding.executePendingBindings()
//            notification.customer?.avatar?.let {
//                Picasso.get().load(it).into(binding.imageUrl)
//            }
        }
    }
}
