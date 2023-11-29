package com.example.sweetflowershop.data.model.order

import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.data.model.voucher.Voucher

data class Order(
    val addressId: Long? = null,
    val voucherId: Long? = null,
    val discount: Double = 0.0,
    val amount: Double = 0.0,
    val paymentOnline: Boolean = false,
    val note: String? = null,
    val shipPrice: Double = 0.0,
    val totalPrice: Double = 0.0,
    val vouchers: List<Voucher>? = null,
    val cartItems: List<CartItem>? = null
) {
    override fun toString(): String {
        return "Order(addressId=$addressId,  discount= $discount, vouchers=$vouchers, voucherId=$voucherId, paymentOnline=$paymentOnline, note=$note, shipPrice=$shipPrice, totalPrice=$totalPrice, cartItems=$cartItems)"
    }
}
