package com.example.sweetflowershop.data.model.review

import com.google.gson.annotations.SerializedName

data class ReviewsWrapper(
    @SerializedName("reviews")
    val reviews: List<ReviewContent>
)