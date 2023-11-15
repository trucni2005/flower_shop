package com.example.sweetflowershop.data.model.category
import java.io.Serializable

data class Category(
    val id: Int,
    val name: String,
    val image: String,
    val detail: String
): Serializable