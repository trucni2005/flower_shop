package com.example.sweetflowershop.data.api

import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.data.model.address.AddressToSend
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AddressAPI {
    @GET("address")
    fun getAddressList(@Header("Cookie") token: String): Single<List<Address>>
    @POST("address/create-address")
    fun addAddress(@Header("Cookie") token: String, @Body address: AddressToSend): Observable<AccountModel>
//
//    @PUT("user/address/{addressId}")
//    fun updateAddress(
//        @Header("Authorization") token: String,
//        @Path("addressId") addressId: String,
//        @Body address: UserAddress
//    ): Single<ApiResponse>
//
//    @DELETE("user/address/{addressId}")
//    fun deleteAddress(@Header("Authorization") token: String, @Path("addressId") addressId: String): Single<ApiResponse>
}
