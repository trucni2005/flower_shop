package com.example.sweetflowershop.data.model.order

import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.data.model.voucher.Voucher

data class Payment(
    val order: Order,
    val voucher: Voucher,
    val address: Address,
    val methodPayment: String,
    val urlQR: String
)
