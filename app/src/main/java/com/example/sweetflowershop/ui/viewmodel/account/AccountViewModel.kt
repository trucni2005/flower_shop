package com.example.sweetflowershop.ui.viewmodel.account

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetflowershop.data.model.customer_account.Account
import com.example.sweetflowershop.network.apiService.account.AccountAPIService
import com.example.sweetflowershop.ui.view.main.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AccountViewModel : ViewModel() {
    private val apiServices = AccountAPIService()
    private val _accountLiveData = MutableLiveData<Account>()

    val accountLiveData: LiveData<Account> = _accountLiveData

    private var account: Account? = null


    fun fetchAccount(context: Context) {
        val token = getToken(context)

        if (!token.isNullOrEmpty()) {
            apiServices.getAccountInformation(token)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                    { account ->
                        _accountLiveData.value = account.result
                        Log.d("Account", account.result.toString())
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