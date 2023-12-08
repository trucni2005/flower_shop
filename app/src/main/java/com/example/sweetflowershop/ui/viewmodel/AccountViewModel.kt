package com.example.sweetflowershop.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.customer_account.Account
import com.example.sweetflowershop.data.repository.AccountRepository
import com.example.sweetflowershop.ui.view.main.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AccountViewModel : ViewModel() {
    private val apiServices = AccountRepository()

    private val _accountLiveData = MutableLiveData<Account>()
    val accountLiveData: LiveData<Account> = _accountLiveData

    private val _sharedAccountLiveData = MutableLiveData<Account>()
    val sharedAccountLiveData: LiveData<Account> = _sharedAccountLiveData

    fun fetchAccount(context: Context) {
        val token = getToken(context)

        if (!token.isNullOrEmpty()) {
            apiServices.getAccountInformation(token)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                    { accountModel ->
                        val account = accountModel.result

                        // Cập nhật thông tin tài khoản trong LiveData
                        _accountLiveData.value = account

                        // Cập nhật thông tin tài khoản trong LiveData được chia sẻ
                        updateSharedAccount(account)

                        Log.d("Account", account.toString())
                    },
                    { e ->
                        Log.d("Test", "Error: ${e.message}")
                    }
                )
        } else {
            launchLoginActivity(context)
        }
    }

    fun getAccount(context: Context): Account? {
        if (accountLiveData.value == null) {
            fetchAccount(context)
        }
        return accountLiveData.value
    }

    private fun updateSharedAccount(account: Account) {
        _sharedAccountLiveData.value = account
        Log.d("DEBUG TEST", _sharedAccountLiveData.value.toString())
    }

    private fun getToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("Authorization", null)
    }

    private fun launchLoginActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("shouldLaunchLoginActivity", true)
        context.startActivity(intent)
    }
}
