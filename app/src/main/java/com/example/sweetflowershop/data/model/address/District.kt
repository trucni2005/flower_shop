package com.example.sweetflowershop.data.model.address

data class District(
    val name: String,
    val code: Int,
    val codename: String,
    val division_type: String,
    val wards: List<Ward>
)