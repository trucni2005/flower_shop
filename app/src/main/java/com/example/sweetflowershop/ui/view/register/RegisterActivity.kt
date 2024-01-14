package com.example.sweetflowershop.ui.view.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.databinding.ActivityRegisterBinding
import com.example.sweetflowershop.ui.view.verifyByEmail.VerifyByEmail
import com.example.sweetflowershop.data.repository.AccountRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val accountAPIService = AccountRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        val submitButton = binding.btnSignUp
        submitButton.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val reenterPassword = binding.etRepass.text.toString()
            val phoneNumber = binding.etPhone.text.toString()

            if (password != reenterPassword) {
                showToast("Mật khẩu không khớp")
            } else {
                if (!isEmailValid(email)) {
                    showToast("Email không hợp lệ")
                } else if (phoneNumber.length < 10) {
                    showToast("Số điện thoại phải có ít nhất 10 số")
                } else {
                    registerUser(username, password, email, phoneNumber)
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return Pattern.matches(emailPattern, email)
    }

    private fun registerUser(username: String, password: String, email: String, phoneNumber: String) {
        accountAPIService.verify_email(username, password, email, phoneNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { accountModel ->
                    if (accountModel.success) {
                        handleSuccess(username, password, email, phoneNumber, accountModel.message)
                    } else {
                        handleFailure(accountModel.message)
                    }
                },
                { throwable ->
                    handleFailure(throwable.message)
                    throwable.printStackTrace()
                }
            )
    }

    private fun handleSuccess(
        username: String,
        password: String,
        email: String,
        phoneNumber: String,
        successMessage: String
    ) {
        showToast("Đăng ký thành công. Message: $successMessage")
        val bundle = Bundle().apply {
            putString("request", "0")
            putString("id", successMessage)
            putString("username", username)
            putString("password", password)
            putString("email", email)
            putString("phoneNumber", phoneNumber)
        }
        val intent = Intent(this, VerifyByEmail::class.java).apply { putExtras(bundle) }
        startActivity(intent)
    }

    private fun handleFailure(errorMessage: String?) {
        Log.e("DEBUG", "Đăng ký không thành công. Message: $errorMessage")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
