package com.example.sweetflowershop.ui.view.verifyByEmail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.ui.view.main.MainActivity
import com.example.sweetflowershop.data.repository.AccountRepository
import com.example.sweetflowershop.databinding.VerifyByEmailBinding
import com.example.sweetflowershop.ui.view.forgotPassword.InputNewPasswordActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class VerifyByEmail : AppCompatActivity() {
    private lateinit var binding: VerifyByEmailBinding
    private val accountAPIService = AccountRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = VerifyByEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request = intent.getStringExtra("request")
        val id = intent.getStringExtra("id")
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val email = intent.getStringExtra("email")
        val phoneNumber = intent.getStringExtra("phoneNumber")

        binding.btnSubmit.setOnClickListener {
            val otp = binding.etVerificationCode.text.toString()
            id?.let {
                verifyOTP(it, otp, request, username, password, email, phoneNumber)
            }
        }
    }

    private fun verifyOTP(
        id: String,
        otp: String,
        request: String?,
        username: String?,
        password: String?,
        email: String?,
        phoneNumber: String?
    ) {
        accountAPIService.verifyOTP(id, otp)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { accountModel ->
                    if (accountModel.success) {
                        handleOTPSuccess(request, username, password, email, phoneNumber)
                    } else {
                        handleOTPFailure(accountModel.message)
                    }
                },
                { throwable ->
                    handleOTPFailure(throwable.message)
                    throwable.printStackTrace()
                }
            )
    }

    private fun handleOTPSuccess(
        request: String?,
        username: String?,
        password: String?,
        email: String?,
        phoneNumber: String?
    ) {
        Log.e("DEBUG", "Verify OTP Success")
        when (request) {
            "0" -> performRegistration(username, password, email, phoneNumber)
            else -> performForgotPassword(username)
        }
    }

    private fun handleOTPFailure(errorMessage: String?) {
        Log.e("DEBUG", "Verify OTP failed. Message: $errorMessage")
        showToast("Xác thực bằng OTP thất bại!")
    }

    private fun performRegistration(username: String?, password: String?, email: String?, phoneNumber: String?) {
        if (username != null && password != null && email != null && phoneNumber != null) {
            accountAPIService.register(username, password, email, phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { accountModel ->
                        if (accountModel.success) {
                            showToast("Đăng ký thành công!")
                            navigateToHomeFragment()
                        } else {
                            handleRegistrationFailure(accountModel.message)
                        }
                    },
                    { throwable ->
                        handleRegistrationFailure(throwable.message)
                        throwable.printStackTrace()
                    }
                )
        } else {
            showToast("Đăng ký thất bại!")
        }
    }

    private fun handleRegistrationFailure(errorMessage: String?) {
        Log.e("DEBUG", "Registration failed. Message: $errorMessage")
        showToast("Đăng ký thất bại!")
    }

    private fun performForgotPassword(username: String?) {
        navigateToForgotPasswordFragment(username)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToHomeFragment() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("shouldLaunchLoginActivity", true)
        startActivity(intent)
    }

    private fun navigateToForgotPasswordFragment(username: String?) {
        val intent = Intent(this, InputNewPasswordActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }
}
