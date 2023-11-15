package com.example.sweetflowershop.data.repo.category

import com.example.sweetflowershop.data.model.category.Category
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CategoriesAPI {
        @GET("category")
        fun getCategories(): Single<List<Category>>

}