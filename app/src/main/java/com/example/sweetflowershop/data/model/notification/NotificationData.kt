package com.example.sweetflowershop.data.model.notification

import java.time.LocalDateTime

data class NotificationData(
    val id: Long,
    val type: String,
    val title: String,
    val content: String,
    val image: String,
    val datetime: String,
    val haveRead: Boolean
)