package com.example.sweetflowershop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.category.Category
import com.example.sweetflowershop.data.repository.CategoryRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryViewModel : ViewModel() {

    private val categoryApiServices = CategoryRepository()

    private val categoriesLiveData = MutableLiveData<List<Category>>()

    fun getCategoriesLiveData(): LiveData<List<Category>> {
        return categoriesLiveData
    }

    fun fetchCategories() {
        categoryApiServices.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { receivedCategories ->
                    categoriesLiveData.value = receivedCategories
                    Log.d("DEBUG", "Received categories: $receivedCategories")
                },
                { error ->
                    Log.d("DEBUG", "Fail ${error.message}")
                }
            )
    }
}
