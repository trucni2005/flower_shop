package com.example.sweetflowershop.data.repository

import com.example.sweetflowershop.data.api.VoucherAPI
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.model.voucher.Voucher
import io.reactivex.rxjava3.core.Single

class VoucherResponsitory {
    private val api: VoucherAPI = ApiClient.createService(VoucherAPI::class.java)

    fun getVouchers(token: String): Single<List<Voucher>> {
        return api.getVouchers(token)
    }
}