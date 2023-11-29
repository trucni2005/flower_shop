package com.example.sweetflowershop.data.api

import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.data.model.cart.CartToSend
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface CartAPI {

    @Headers("Content-Type: application/json")
    @GET("cart")
    fun getCartItems(
        @Header("Cookie") token: String,
    ): Single<List<CartItem>>

    @Headers("Content-Type: application/json")
    @POST("cart/update")
    fun updateCartItemQuantity(
        @Header("Cookie") token: String,
        @Body cartToSend: CartToSend
    ): Observable<AccountModel>
}