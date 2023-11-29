package com.example.sweetflowershop.data.model.order

import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.data.model.voucher.Voucher

data class CheckoutData(
    val checkoutItems: List<CartItem>,
    val totalPrice: Double,
    val shipPrice: Double,
    val discount: Double,
    val amount: Double,
    val vouchers: List<Voucher>
)
