package com.example.sweetflowershop.data.model.customer_account

data class AccountRequest(
    val username: String,
    val password: String,
    val email: String,
    val phone: String
)