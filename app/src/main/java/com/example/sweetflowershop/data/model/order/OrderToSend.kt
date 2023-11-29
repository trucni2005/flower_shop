package com.example.sweetflowershop.data.model.order

data class OrderToSend(val addressId: Int, val voucherId: Long?, val paymentOnline: Boolean, val note: String?)
