package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.api.ProductsAPI
import io.reactivex.rxjava3.core.Single
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.model.review.Review
import io.reactivex.rxjava3.core.Observable

class ProductRepository {
    private val api: ProductsAPI = ApiClient.createService(ProductsAPI::class.java)

    fun getProducts(): Single<List<Product>> {
        return api.getProducts()
    }

    fun getRelatedProducts(product_id: Int): Single<List<Product>> {
        return api.getRelatedProduct(product_id)
    }

    fun getFlashSaleProduct(): Single<List<Product>> {
        return api.getFlashSaleProduct()
    }

    fun getBestSellerProduct(): Single<List<Product>> {
        return api.getBestSellerProduct()
    }

    fun getProductsbyCategory(categoryId: Int): Single<List<Product>> {
        return api.getProductsbyCategory(categoryId)
    }

    fun addToCart(token: String, productId: Int): Observable<AccountModel> {
        return api.addToCart(token, productId)
    }

    fun getReviewsbyProduct(productId: Int): Single<List<Review>> {
        return api.getReviewsbyProduct(productId)
    }

//
//    fun searchProduct(search: String): Single<List<Product>> {
//        return api.searchProduct(search)
//    }
}