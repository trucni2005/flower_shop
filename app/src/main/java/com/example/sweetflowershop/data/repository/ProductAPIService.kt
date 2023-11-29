package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.api.ProductsAPI
import io.reactivex.rxjava3.core.Single
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import io.reactivex.rxjava3.core.Observable

class ProductAPIService {
    private val api: ProductsAPI = ApiClient.createService(ProductsAPI::class.java)

    fun getProducts(): Single<List<Product>> {
        return api.getProducts()
    }

    fun getProductsbyCategory(categoryId: Int): Single<List<Product>> {
        return api.getProductsbyCategory(categoryId)
    }

    fun addToCart(token: String, productId: Int): Observable<AccountModel> {
        return api.addToCart(token, productId)
    }

//
//    fun searchProduct(search: String): Single<List<Product>> {
//        return api.searchProduct(search)
//    }
}