package com.example.sweetflowershop.data.model

import retrofit2.http.Headers

data class ApiResponse<T>(
    val data: T,
    val headers: Headers
)
