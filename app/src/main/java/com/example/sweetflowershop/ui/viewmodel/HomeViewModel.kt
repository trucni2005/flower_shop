package com.example.sweetflowershop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repository.ProductAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val apiServices = ProductAPIService()
    private val _productsLiveData = MutableLiveData<List<Product>>()
    private val _flashSaleProductsLiveData = MutableLiveData<List<Product>>()
    private val _filteredProductsLiveData = MutableLiveData<List<Product>>()
    private val _bestSellerProductsLiveData = MutableLiveData<List<Product>>()

    val productsLiveData: LiveData<List<Product>> = _productsLiveData
    val flashSaleProductsLiveData: LiveData<List<Product>> = _flashSaleProductsLiveData
    val bestSellerProductsLiveData: LiveData<List<Product>> = _bestSellerProductsLiveData
    val filteredProductsLiveData: LiveData<List<Product>> = _filteredProductsLiveData

    fun filterProducts(query: String?) {
        val originalProducts = _productsLiveData.value.orEmpty()
        val filteredProducts = if (query.isNullOrBlank()) {
            originalProducts
        } else {
            val filteredList = originalProducts.filter { it.name.contains(query, ignoreCase = true) }
            logFilteredProducts(query, filteredList)
            filteredList
        }
        _filteredProductsLiveData.value = filteredProducts
    }

    private fun logFilteredProducts(query: String?, filteredList: List<Product>) {
        Log.d("HomeViewModel", "Filter Query: $query")
        Log.d("HomeViewModel", "Filtered Products:")
        for (product in filteredList) {
            Log.d("HomeViewModel", product.name)
        }
    }

    fun fetchProducts() {
        fetchFromApi { apiServices.getProducts() }
    }

    fun fetchFlashSaleProducts() {
        fetchFromApi { apiServices.getProducts() }
    }

    fun fetchBestSellerProducts() {
        fetchFromApi { apiServices.getProducts() }
    }

    private fun fetchFromApi(apiCall: () -> Single<List<Product>>) {
        apiCall.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                override fun onSuccess(products: List<Product>) {
                    _productsLiveData.value = products
                    _flashSaleProductsLiveData.value = products
                    _bestSellerProductsLiveData.value = products
                }

                override fun onError(e: Throwable) {
                    // Xử lý lỗi, bạn có thể muốn ghi log hoặc hiển thị thông báo lỗi
                }
            })
    }
}
