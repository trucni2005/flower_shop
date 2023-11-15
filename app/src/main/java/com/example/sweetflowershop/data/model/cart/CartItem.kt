package com.example.sweetflowershop.data.model.cart

import com.example.sweetflowershop.data.model.product.Product
import java.io.Serializable

data class CartItem(
    var id: Int = 0,
    var cart_id: Int = 0,
    var product: Product,
    var quantity: Int = 0
): Serializable


