package com.example.sweetflowershop.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.product.Product

class ProductDetailViewModel : ViewModel() {
    private val productLiveData = MutableLiveData<Product>()

    fun getProductLiveData(): LiveData<Product> {
        return productLiveData
    }

    fun setProduct(product: Product) {
        productLiveData.value = product
    }
}