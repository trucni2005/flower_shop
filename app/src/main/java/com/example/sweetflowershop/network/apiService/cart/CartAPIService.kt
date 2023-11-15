package com.example.sweetflowershop.viewmodel

import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.data.model.cart.CartToSend
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.repo.cart.CartAPI
import com.example.sweetflowershop.network.apiService._Constant
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CartAPIService {
    private val baseUrl = _Constant.baseUrl
    private val api: CartAPI = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(CartAPI::class.java)

    fun getCartItems(token: String): Single<List<CartItem>> {
        return api.getCartItems(token)
    }

    fun updateCartItemQuantity(token: String, cartToSend: CartToSend): Observable<AccountModel> {
        return api.updateCartItemQuantity(token, cartToSend)
    }

}