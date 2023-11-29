package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.model.category.Category
import com.example.sweetflowershop.data.api.CategoriesAPI
import io.reactivex.rxjava3.core.Single

class CategoryAPIService {
    private val api: CategoriesAPI = ApiClient.createService(CategoriesAPI::class.java)

    fun getCategories(): Single<List<Category>> {
        return api.getCategories()
    }
}
