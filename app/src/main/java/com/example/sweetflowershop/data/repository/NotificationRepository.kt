package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.api.NotificationAPI
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.model.notification.NotificationData
import com.example.sweetflowershop.data.model.review.ReviewContent
import com.example.sweetflowershop.data.model.review.ReviewsWrapper
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class NotificationRepository{
    private val api: NotificationAPI = ApiClient.createService(NotificationAPI::class.java)

    fun getAllNotification(
        token: String
    ): Single<List<NotificationData>>
    {
        return api.getAllNotification(token)
    }

}