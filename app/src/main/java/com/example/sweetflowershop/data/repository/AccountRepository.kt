package com.example.sweetflowershop.data.repository
import com.example.sweetflowershop.data.model.customer_account.AccountInformation
import com.example.sweetflowershop.data.model.customer_account.AccountInformationResponse
import com.example.sweetflowershop.data.model.customer_account.AccountLogin
import com.example.sweetflowershop.data.api.AccountAPI
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.data.model.customer_account.AccountRequest
import com.example.sweetflowershop.data.model.customer_account.ForgotPasswordRequest
import com.example.sweetflowershop.data.model.customer_account.LoginResponse
import com.example.sweetflowershop.data.model.customer_account.Otp
import io.reactivex.rxjava3.core.Observable

class AccountRepository {
    private var api: AccountAPI = ApiClient.createService(AccountAPI::class.java)

    fun verify_email(
        username: String,
        password: String,
        email: String,
        phone: String
    ): Observable<AccountModel> {
        return api.verifyEmail(AccountRequest(username, password, email, phone))
    }

    fun verifyOTP(
        id: String,
        otp: String
    ): Observable<AccountModel> {
        return api.verifyOTP(Otp(id, otp))
    }

    fun register(
        username: String,
        password: String,
        email: String,
        phone: String
    ): Observable<AccountModel> {
        return api.register(AccountRequest(username, password, email, phone))
    }

    fun login(username: String, password: String): Observable<LoginResponse>? {
        if (username != null && password != null) {
            return api.login(AccountLogin(username, password))
        }
        return Observable.empty()
    }



    fun forgotPassword(username: String): Observable<AccountModel>? {
        if (username != null) {
            return api.forgotPassword(ForgotPasswordRequest(username))
        }
        return null
    }

    fun resetPassword(username: String, password: String): Observable<AccountModel>? {
        if (username != null && password != null) {
            return api.resetPassword(AccountLogin(username, password))
        }
        return null
    }

    fun getAccountInformation(token: String): Observable<AccountInformationResponse>? {
        if (token != null) {
            return api.getAccountInformation(token)
        }
        return null
    }

    fun updateAccountInformation(token: String, accountInformation: AccountInformation): Observable<AccountInformationResponse>? {
        if (token != null) {
            return api.updateAccountInformation(token, accountInformation)
        }
        return null
    }

}