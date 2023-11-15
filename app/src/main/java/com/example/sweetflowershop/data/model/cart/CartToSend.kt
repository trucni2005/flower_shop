package com.example.sweetflowershop.data.model.cart

import java.io.Serializable

data class CartToSend(
    var id: Int = 0,
    var quantity: Int = 0
): Serializable
