package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.model.order.Order
import com.example.sweetflowershop.data.model.order.OrderToSend
import com.example.sweetflowershop.data.model.order.Payment
import com.example.sweetflowershop.data.api.OrderAPI
import com.example.sweetflowershop.data.model.order.OrderHistory
import io.reactivex.rxjava3.core.Single

class OrderRepository {
    private val api: OrderAPI = ApiClient.createService(OrderAPI::class.java)

    fun createOrder(token: String, orderToSend: OrderToSend): Single<Order> {
        return api.createOrder(token, orderToSend)
    }

    fun confirmOrder(token: String, orderToSend: OrderToSend): Single<Payment> {
        return api.confirmOrder(token, orderToSend)
    }

    fun getOrderHistory(token: String): Single<List<OrderHistory>> {
        return api.getOrderHistory(token)
    }
}
