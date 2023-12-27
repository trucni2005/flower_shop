package com.example.sweetflowershop.data.model.order

import java.io.Serializable

data class OrderDetailHistory (
    val id: Int,
    val orderHistory_id: Int,
    val productId: Int,
    val nameProduct: String,
    val  priceProduct: Long,
    val quantity: Int,
    val image: String,
    var rating: Int,
    var content: String
): Serializable
