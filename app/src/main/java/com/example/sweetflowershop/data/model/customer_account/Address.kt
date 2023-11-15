package com.example.sweetflowershop.data.model.customer_account

import java.io.Serializable

data class Address(
    val city: String,
    val district: String,
    val commune: String,
    val streetAddress: String,
    val note: String
): Serializable