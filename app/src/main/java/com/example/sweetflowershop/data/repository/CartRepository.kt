package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.data.model.cart.CartToSend
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.api.CartAPI
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class CartRepository {
    private val api: CartAPI = ApiClient.createService(CartAPI::class.java)

    fun getCartItems(token: String): Single<List<CartItem>> {
        return api.getCartItems(token)
    }

    fun updateCartItemQuantity(token: String, cartToSend: CartToSend): Observable<AccountModel> {
        return api.updateCartItemQuantity(token, cartToSend)
    }

}