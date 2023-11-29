package com.example.sweetflowershop.ui.viewmodel.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repository.ProductAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val apiServices = ProductAPIService()
    private val _productsLiveData = MutableLiveData<List<Product>>()

    val productsLiveData: LiveData<List<Product>> = _productsLiveData

    fun fetchProducts() {
        apiServices.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                override fun onSuccess(products: List<Product>) {
                    _productsLiveData.value = products
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}