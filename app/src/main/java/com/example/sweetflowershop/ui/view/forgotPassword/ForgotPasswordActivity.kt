package com.example.sweetflowershop.ui.view.forgotPassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.databinding.ActivityForgotPassBinding
import com.example.sweetflowershop.ui.view.verifyByEmail.VerifyByEmail
import com.example.sweetflowershop.network.apiService.account.AccountAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ForgotPasswordActivity: AppCompatActivity() {
    private lateinit var binding: ActivityForgotPassBinding
    private val accountAPIService = AccountAPIService()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnForgotNext.setOnClickListener {
            val username = binding.etForgotUsername.text.toString()
            sendEmail(username)
        }
    }

    private fun sendEmail(username: String) {
        Log.d("username", username)
            accountAPIService.forgotPassword(username)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.let {
                    compositeDisposable.add(
                        it
                            .subscribe { accountModel ->
                                if (accountModel.success) {
                                    val successMessage = accountModel.message
                                    Log.d("DEBUG", "Đăng ký thành công. Message: $successMessage")
                                    val bundle = Bundle()
                                    val intent = Intent(this, VerifyByEmail::class.java)
                                    intent.putExtras(bundle)
                                    startActivity(intent)
                                }
                            });
                }
    }
}