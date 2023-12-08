package com.example.sweetflowershop.data.model.review

import com.example.sweetflowershop.data.model.customer_account.Account

data class Review(
    val id: Int,
    val customer: Account,
    val rate: Int,
    val content: String,
    val name: String,
    val date: String
)
