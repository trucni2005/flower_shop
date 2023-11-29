package com.example.sweetflowershop.data.model.customer_account

data class AccountInformation(
    val avatar: ByteArray?,
    val phone: String,
    val fullName: String,
    val email: String,
    val sex: Boolean,
    val birthday: String
)

