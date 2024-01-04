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
        val view = binding.root
        setContentView(view)

        val request = intent.getStringExtra("request")
        val id = intent.getStringExtra("id")
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val email = intent.getStringExtra("email")
        val phoneNumber = intent.getStringExtra("phoneNumber")

        binding.btnSubmit.setOnClickListener {
            val otp = binding.etVerificationCode.text.toString()
            if (id != null) {
                accountAPIService.verifyOTP(id, otp)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { accountModel ->
                            if (accountModel.success) {
                                Log.e("DEBUG", "Verify OTP Success")
                                val successMessage = accountModel.message
                                if (request == "0")
                                {
                                    Log.e("DEBUG", "Verify OTP Success. REQUEST: Login")
                                    performRegistration(username, password, email, phoneNumber)
                                }
                                else
                                {
                                    Log.e("DEBUG", "Verify OTP Success. REQUEST: Foget Password")
                                    performFogotPassword(username)
                                }
                            } else {
                                val errorMessage = accountModel.message
                                Log.e("DEBUG", "Verify OTP failed. Message: $errorMessage")
                                // Handle verification failure
                            }
                        },
                        { throwable ->
                            Log.e("DEBUG", "Error occurred during OTP verification: ${throwable.message}")
                            throwable.printStackTrace()
                            // Handle error during OTP verification
                        }
                    )
            }
        }
    }

    private fun performRegistration(username: String?, password: String?, email: String?, phoneNumber: String?) {
        if (username != null && password != null && email != null && phoneNumber != null) {
            accountAPIService.register(username, password, email, phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { accountModel ->
                        if (accountModel.success) {
                            val successMessage = accountModel.message
                            Toast.makeText(this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show()
                            navigateToHomeFragment()
                        } else {
                            val errorMessage = accountModel.message
                            Log.e("DEBUG", "Registration failed. Message: $errorMessage")
                            // Handle registration failure
                        }
                    },
                    { throwable ->
                        Log.e("DEBUG", "Error occurred during registration: ${throwable.message}")
                        throwable.printStackTrace()
                        // Handle error during registration
                    }
                )
        } else {
            // Handle case where information is invalid or missing
        }
    }

    private fun performFogotPassword(username: String?) {
        navigateToFogotPasswordFragment(username)
    }

    private fun navigateToHomeFragment() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("shouldLaunchLoginActivity", true)
        startActivity(intent)
    }

    private fun navigateToFogotPasswordFragment(username: String?) {
        val intent = Intent(this, InputNewPasswordActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }
}
