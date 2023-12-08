package com.example.sweetflowershop.data.api

import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.model.review.Review
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsAPI {
    @GET("product")
    fun getProducts(): Single<List<Product>>

    @GET("product")
    fun getProductsbyCategory(
        @Query("categoryId") categoryId: Int
    ): Single<List<Product>>

    @GET("review/{product_id}")
    fun getReviewsbyProduct(
        @Path("product_id") product_id: Int
    ): Single<List<Review>>

    @Headers("Content-Type: application/json")
    @GET("cart/add/{product_id}")
    fun addToCart(
        @Header("Cookie") token: String,
        @Path("product_id") product_id: Int
    ): Observable<AccountModel>

}