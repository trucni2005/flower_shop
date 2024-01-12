package com.example.sweetflowershop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repository.ProductRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductDetailViewModel : ViewModel() {
    private val apiServices = ProductRepository()

    private val productLiveData = MutableLiveData<Product>()
    private val _relatedProductsLiveData = MutableLiveData<List<Product>>()

    val relatedProductsLiveData: LiveData<List<Product>> = _relatedProductsLiveData

    fun getProductLiveData(): LiveData<Product> {
        return productLiveData
    }

    fun setProduct(product: Product) {
        productLiveData.value = product
    }

    fun fetchRelatedProducts(productId: Int) {
        Log.d("ProductDetailViewModel", "Fetching Related Products for productId: $productId")
        apiServices.getRelatedProducts(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                override fun onSuccess(products: List<Product>) {
                    _relatedProductsLiveData.value = products
                    Log.d("ProductDetailViewModel", "Related Products Fetched Successfully:")
                    for (product in products) {
                        Log.d("ProductDetailViewModel", product.name)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("ProductDetailViewModel", "Error fetching related products: ${e.message}")
                    // Xử lý lỗi API
                }
            })
    }
}
