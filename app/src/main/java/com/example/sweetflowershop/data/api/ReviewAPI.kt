package com.example.sweetflowershop.data.api

import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.model.review.ReviewContent
import com.example.sweetflowershop.data.model.review.ReviewsWrapper
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewAPI {
    @Headers("Content-Type: application/json")
    @POST("review/add-review/{order_id}")
    fun createReviews(
        @Header("Cookie") token: String,
        @Path("order_id") orderId: Long?,
        @Body reviewsWrapper: ReviewsWrapper
    ): Observable<AccountModel>
}