package com.example.sweetflowershop.data.model.address

data class City(
    val name: String,
    val code: Int,
    val codename: String,
    val division_type: String,
    val phone_code: Int,
    val districts: List<District>
)