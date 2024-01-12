package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.model.address.City
import com.example.sweetflowershop.data.api.CityAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.create

class CityRepository {

    private val cityAPI = RetrofitClient.instance.create<CityAPI>()

    fun fetchCities(
        onSuccess: (List<City>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        cityAPI.getCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cities ->
                    onSuccess(cities)
                },
                { error ->
                    onError(error)
                }
            )
    }
}