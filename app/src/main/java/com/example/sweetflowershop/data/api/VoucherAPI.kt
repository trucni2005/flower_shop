package com.example.sweetflowershop.data.api
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.model.voucher.Voucher
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface VoucherAPI {
    @Headers("Content-Type: application/json")
    @GET("voucher")
    fun getVouchers(
        @Header("Cookie") token: String
    ): Single<List<Voucher>>
}