package com.example.sweetflowershop.data.model.customer_account

import java.time.LocalDate

data class AccountInformation(
    val phone: String,
    val fullName: String,
    val email: String,
    val sex: Boolean,
    val birth: String
)

