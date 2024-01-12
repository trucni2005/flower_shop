package com.example.sweetflowershop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.review.Review
import com.example.sweetflowershop.data.repository.ProductRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ReviewViewModel : ViewModel() {
    private val apiServices = ProductRepository() // Thay thế bằng API service của bạn
    private val _reviewsLiveData = MutableLiveData<List<Review>>()

    val reviewsLiveData: LiveData<List<Review>> = _reviewsLiveData

    fun fetchReviews(productId: Int) {
        apiServices.getReviewsbyProduct(productId) // Thay thế bằng phương thức lấy dữ liệu đánh giá của bạn
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Review>>() {
                override fun onSuccess(reviews: List<Review>) {
                    _reviewsLiveData.value = reviews
                    Log.d("fetchReviews", reviews.toString())
                }

                override fun onError(e: Throwable) {
                    // Xử lý lỗi nếu cần thiết
                }
            })
    }

    override fun onCleared() {
        // Gọi khi ViewModel được hủy
        super.onCleared()
        // Huỷ bất kỳ nguồn dữ liệu không đồng bộ nếu cần
    }
}
