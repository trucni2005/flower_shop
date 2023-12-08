package com.example.sweetflowershop.data.api

import com.example.sweetflowershop.data.model.order.Order
import com.example.sweetflowershop.data.model.order.OrderHistory
import com.example.sweetflowershop.data.model.order.OrderToSend
import com.example.sweetflowershop.data.model.order.Payment
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface OrderAPI {
    @Headers("Content-Type: application/json")
    @POST("check-out")
    fun createOrder(
        @Header("Cookie") token: String,
        @Body orderToSend: OrderToSend
    ): Single<Order>

    @Headers("Content-Type: application/json")
    @POST("payment/app")
    fun confirmOrder(
        @Header("Cookie") token: String,
        @Body orderToSend: OrderToSend
    ): Single<Payment>

    @Headers("Content-Type: application/json")
    @GET("order-history")
    fun getOrderHistory(
        @Header("Cookie") token: String,
    ): Single<List<OrderHistory>>
}
