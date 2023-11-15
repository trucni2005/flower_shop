package com.example.sweetflowershop.data.model.product
import com.example.sweetflowershop.data.model.review.Review
import java.io.Serializable

data class Product(
    val id: Int,
    val name: String,
    val original_price: String,
    val discount: String,
    val price: String,
    val description: String,
    val image1: String,
    val image2: String,
    val image3: String,
    val image4: String,
    val image5: String,
    val overall_rating: String,
    val delivery: String,
    val subInfo: String,
    val reviews: List<Review>,
    val details: String,
    val deleted: Boolean
): Serializable
