package com.example.sweetflowershop.data.model.voucher

import java.io.Serializable

data class Voucher(
    val id: Long? = null,
    val code: String? = null,
    val percentage: Double = 0.0,
    val usageLimit: Int = 0,
    val startDate: String? = null,
    val endDate: String? = null,
    val conditionPrice: Double = 0.0,
    val expired: Boolean = false,
    val title: String
): Serializable