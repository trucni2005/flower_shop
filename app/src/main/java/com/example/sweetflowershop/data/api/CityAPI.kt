package com.example.sweetflowershop.data.api

import com.example.sweetflowershop.data.model.address.City
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CityAPI {
    @GET("/api/?depth=3")
    fun getCities(): Single<List<City>>
}