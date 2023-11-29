package com.example.sweetflowershop.data.model.address

import java.io.Serializable

data class Address(
    val id: String,
    val nameCustomer: String,
    val phoneNumber: String,
    val city: String,
    val district: String,
    val ward: String,
    val street: String,
): Serializable