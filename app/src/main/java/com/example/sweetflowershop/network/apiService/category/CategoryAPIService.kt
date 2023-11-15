package com.example.sweetflowershop.network.apiService.category

import com.example.sweetflowershop.data.model.category.Category
import com.example.sweetflowershop.data.repo.category.CategoriesAPI
import com.example.sweetflowershop.network.apiService._Constant
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CategoryAPIService {
    private val baseUrl = _Constant.baseUrl
    private val api: CategoriesAPI = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(CategoriesAPI::class.java)

    fun getCategories(): Single<List<Category>> {
        return api.getCategories()
    }
}
