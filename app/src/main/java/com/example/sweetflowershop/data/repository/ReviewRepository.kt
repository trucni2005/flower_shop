package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.api.ReviewAPI
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.model.review.ReviewContent
import com.example.sweetflowershop.data.model.review.ReviewsWrapper
import io.reactivex.rxjava3.core.Observable

class ReviewRepository {
    private val api: ReviewAPI = ApiClient.createService(ReviewAPI::class.java)

    fun createReviews(
        token: String,
        orderId: Long?,
        reviews: List<ReviewContent>
    ): Observable<AccountModel> {
        val reviewsWrapper = ReviewsWrapper(reviews)
        return api.createReviews(token, orderId, reviewsWrapper)
    }

}