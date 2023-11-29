package com.example.sweetflowershop.ui.viewmodel.address

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.data.repository.AddressAPIService
import com.example.sweetflowershop.ui.view.main.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class AddressViewModel : ViewModel() {

    private val addressApiServices = AddressAPIService()

    private val _addressListLiveData = MutableLiveData<List<Address>>()

    val addressListLiveData: LiveData<List<Address>>
        get() = _addressListLiveData

    fun fetchAddressList(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)
        Log.d("AddressModel", token.toString())
        if (!token.isNullOrEmpty()) {
            addressApiServices.getAddressList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Address>>() {
                    override fun onSuccess(addressList: List<Address>) {
                        _addressListLiveData.value = addressList
                        for (address in addressList) {
                            Log.d("AddressViewModel", "Address: $address")
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Test", "Error: ${e.message}")
                    }
                })

        } else {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("shouldLaunchLoginActivity", true)
            context.startActivity(intent)
        }
    }
}