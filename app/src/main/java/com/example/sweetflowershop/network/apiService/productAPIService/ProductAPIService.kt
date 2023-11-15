package com.example.sweetflowershop.network.apiService.productAPIService

import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repo.product.ProductsAPI
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.network.apiService._Constant
import io.reactivex.rxjava3.core.Observable
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPIService {
    private val baseUrl = _Constant.baseUrl
    private val api: ProductsAPI = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ProductsAPI::class.java)

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