package com.example.sweetflowershop.data.api

import com.example.sweetflowershop.data.model.notification.NotificationData
import com.example.sweetflowershop.data.model.order.Order
import com.example.sweetflowershop.data.model.order.OrderToSend
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {
    @Headers("Content-Type: application/json")
    @GET("notification")
    fun getAllNotification(
        @Header("Cookie") token: String
    ): Single<List<NotificationData>>
}