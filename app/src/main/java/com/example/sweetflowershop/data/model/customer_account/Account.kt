package com.example.sweetflowershop.data.model.customer_account

import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.data.model.order.Order
import com.example.sweetflowershop.data.model.review.Review
import java.io.Serializable

data class Account(
    val id: Int,
    val avatar: String,
    val email: String,
    val fullName: String,
    val sex: Boolean,
    val birthday: String,
    val phone: String,
    val username: String,
    val addresses: List<Address>,
    val orders: List<Order>,
    val reviews: List<Review>
): Serializable
