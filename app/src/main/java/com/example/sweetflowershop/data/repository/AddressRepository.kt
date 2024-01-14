package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.data.model.address.AddressToSend
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.api.AddressAPI
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class AddressRepository {

    private val api: AddressAPI = ApiClient.createService(AddressAPI::class.java)

    fun getAddressList(token: String): Single<List<Address>> {
        return api.getAddressList(token)
    }

    fun addAddress(token: String, address: AddressToSend): Observable<AccountModel> {
        return api.addAddress(token, address)
    }
}