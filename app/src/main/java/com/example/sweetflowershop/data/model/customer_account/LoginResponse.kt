package com.example.sweetflowershop.data.model.customer_account

data class LoginResponse(
    val isSuccessful: Boolean,
    val message: String,
    val authorization: String
)