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
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeViewModel : ViewModel() {
    private val apiServices = ProductRepository()
    private val _productsLiveData = MutableLiveData<List<Product>>()
    private val _flashSaleProductsLiveData = MutableLiveData<List<Product>>()
    private val _bestSellerProductsLiveData = MutableLiveData<List<Product>>()
    private val _filteredProductsLiveData = MutableLiveData<List<Product>>()

    val productsLiveData: LiveData<List<Product>> = _productsLiveData
    val flashSaleProductsLiveData: LiveData<List<Product>> = _flashSaleProductsLiveData
    val bestSellerProductsLiveData: LiveData<List<Product>> = _bestSellerProductsLiveData
    val filteredProductsLiveData: LiveData<List<Product>> = _filteredProductsLiveData

    private var currentSearchQuery: String? = null
    private val compositeDisposable = CompositeDisposable()

    fun setSearchQueryAndFetchProducts(query: String?) {
        currentSearchQuery = query
        fetchProducts()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun fetchProducts() {
        Log.d("HomeViewModel", "Fetching Products...")
        val productsObservable = apiServices.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val filteredProductsObservable = productsObservable.map { products ->
            filterProductsByQuery(products, currentSearchQuery)
        }

        compositeDisposable.add(
            filteredProductsObservable
                .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                    override fun onSuccess(products: List<Product>) {
                        _productsLiveData.value = products
                        Log.d("HomeViewModel", "Products Fetched Successfully:")
                        for (product in products) {
                            Log.d("HomeViewModel", product.name)
                        }
                    }

                    override fun onError(e: Throwable) {
                        handleApiError(e)
                    }
                })
        )
    }


    fun fetchFlashSaleProducts() {
        Log.d("HomeViewModel", "Fetching Flash Sale Products...")
        apiServices.getFlashSaleProduct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                override fun onSuccess(products: List<Product>) {
                    _flashSaleProductsLiveData.value = products
                    Log.d("HomeViewModel", "Flash Sale Products Fetched Successfully:")
                    for (product in products) {
                        Log.d("HomeViewModel", product.name)
                    }
                }

                override fun onError(e: Throwable) {
                    handleApiError(e)
                }
            })
    }

    fun fetchBestSellerProducts() {
        Log.d("HomeViewModel", "Fetching Best Seller Products...")
        apiServices.getBestSellerProduct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                override fun onSuccess(products: List<Product>) {
                    _bestSellerProductsLiveData.value = products
                    Log.d("HomeViewModel", "Best Seller Products Fetched Successfully:")
                    for (product in products) {
                        Log.d("HomeViewModel", product.name)
                    }
                }

                override fun onError(e: Throwable) {
                    handleApiError(e)
                }
            })
    }

    private fun handleApiError(e: Throwable) {
        Log.e("HomeViewModel", "API Call Error: ${e.message}")
        // Handle errors, you may want to log or display an error message
    }

    private fun filterProductsByQuery(products: List<Product>, query: String?): List<Product> {
        val filteredList = if (query.isNullOrBlank()) {
            products
        } else {
            products.filter { it.name.contains(query, ignoreCase = true) }
        }

        logFilteredProducts(query, filteredList)

        _filteredProductsLiveData.value = filteredList

        return filteredList
    }


    private fun logFilteredProducts(query: String?, filteredList: List<Product>) {
        Log.d("HomeViewModel", "Filter Query: $query")
        Log.d("HomeViewModel", "Filtered Products:")
        for (product in filteredList) {
            Log.d("HomeViewModel", product.name)
        }
    }
}
