package com.example.sweetflowershop.data.api

import com.example.sweetflowershop.data.model.customer_account.AccountInformation
import com.example.sweetflowershop.data.model.customer_account.AccountInformationResponse
import com.example.sweetflowershop.data.model.customer_account.AccountLogin
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.model.customer_account.AccountRequest
import com.example.sweetflowershop.data.model.customer_account.ForgotPasswordRequest
import com.example.sweetflowershop.data.model.customer_account.LoginResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccountAPI{
    @Headers("Content-Type: application/json")
    @POST("register")
    fun register(
        @Body request: AccountRequest
    ): Observable<AccountModel>

    @Headers("Content-Type: application/json")
    @POST("login-customer")
    fun login(
        @Body request: AccountLogin
    ): Observable<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("verify-email/register")
    fun verifyEmail(@Body request: AccountRequest): Observable<AccountModel>

    @Headers("Content-Type: application/json")
    @POST("verify-email/forgot")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Observable<AccountModel>

    @Headers("Content-Type: application/json")
    @POST("forgot-password")
    fun resetPassword(@Body request: AccountLogin): Observable<AccountModel>

    @Headers("Content-Type: application/json")
    @GET("customer")
    fun getAccountInformation(
        @Header("Cookie") token: String,
    ): Observable<AccountInformationResponse>

    @Headers("Content-Type: application/json")
    @POST("customer/update")
    fun updateAccountInformation(
        @Header("Cookie") token: String,
        @Body accountInformation: AccountInformation
    ): Observable<AccountInformationResponse>
}





