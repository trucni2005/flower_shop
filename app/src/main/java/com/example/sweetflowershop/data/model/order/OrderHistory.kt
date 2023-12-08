package com.example.sweetflowershop.data.model.order

import java.io.Serializable

data class OrderHistory(
    var id: Long? = null,
    var orderDateTime: String? = null,
    var totalPrice: Long? = null,
    var discount: Long? = null,
    var amount: Long? = null,
    var userId: Long? = null,
    var fullNameStaff: String? = null,
    var role: String? = null,
    var customerId: Long? = null,
    var fullNameCustomer: String? = null,
    var phoneCustomer: String? = null,
    var emailCustomer: String? = null,
    var orderStatus: String? = null,
    var nameCustomerReceive: String? = null,
    var phoneCustomerReceive: String? = null,
    var address: String? = null,
    var paymentOnline: Boolean = false,
    var shipPrice: Long? = null,
    var orderDetailHistories: List<OrderDetailHistory>? = null
): Serializable

