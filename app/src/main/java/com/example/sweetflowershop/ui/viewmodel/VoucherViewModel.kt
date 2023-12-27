package com.example.sweetflowershop.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.voucher.Voucher
import com.example.sweetflowershop.data.repository.VoucherResponsitory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class VoucherViewModel : ViewModel() {

    private val apiService = VoucherResponsitory()
    private val _vouchersLiveData = MutableLiveData<List<Voucher>>()

    val vouchersLiveData: LiveData<List<Voucher>> = _vouchersLiveData

    fun fetchVouchers(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)
        Log.d("1", token.toString())

        if (token != null) {
            apiService.getVouchers(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Voucher>>() {
                    override fun onSuccess(vouchers: List<Voucher>) {
                        _vouchersLiveData.value = vouchers
                        Log.d("fetchVouchers", vouchers.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e("fetchVouchers", "Error fetching vouchers", e)
                    }
                })
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
