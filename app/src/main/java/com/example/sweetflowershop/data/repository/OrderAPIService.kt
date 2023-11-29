package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.model.order.Order
import com.example.sweetflowershop.data.model.order.OrderToSend
import com.example.sweetflowershop.data.model.order.Payment
import com.example.sweetflowershop.data.api.OrderAPI
import io.reactivex.rxjava3.core.Single

class OrderAPIService {
    private val api: OrderAPI = ApiClient.createService(OrderAPI::class.java)

    fun createOrder(token: String, orderToSend: OrderToSend): Single<Order> {
        return api.createOrder(token, orderToSend)
    }

    fun confirmOrder(token: String, orderToSend: OrderToSend): Single<Payment> {
        return api.confirmOrder(token, orderToSend)
    }
}
