package com.example.sweetflowershop.ui.view.verifyByEmail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.ui.view.forgotPassword.InputNewPasswordActivity
import com.example.sweetflowershop.ui.view.main.MainActivity
import com.example.sweetflowershop.databinding.VerifyByEmailBinding
import com.example.sweetflowershop.network.apiService.account.AccountAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class VerifyByEmail : AppCompatActivity() {
    private lateinit var binding: VerifyByEmailBinding
    private val accountAPIService = AccountAPIService()
    private var shouldLaunchLoginActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = VerifyByEmailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val otpFromIntent = intent.getStringExtra("otp")
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val email = intent.getStringExtra("email")
        val phoneNumber = intent.getStringExtra("phoneNumber")

        binding.btnSubmit.setOnClickListener {
            val enteredOTP = binding.etVerificationCode.text.toString()

            if (enteredOTP == otpFromIntent) {
                Toast.makeText(applicationContext, "OTP đúng!", Toast.LENGTH_SHORT).show()
                if (email != "") {
                    performRegistration(username, password, email, phoneNumber)
                }
                else
                {
                    val intent = Intent(this, InputNewPasswordActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(applicationContext, "OTP không đúng!", Toast.LENGTH_SHORT).show()
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
                            Log.e("DEBUG", "Đăng ký không thành công. Message: $errorMessage")
                            // Xử lý khi đăng ký thất bại
                        }
                    },
                    { throwable ->
                        Log.e("DEBUG", "Lỗi xảy ra: ${throwable.message}")
                        throwable.printStackTrace() // In lỗi ra màn hình
                        // Xử lý khi có lỗi xảy ra
                    }
                )
        } else {
            // Xử lý khi các thông tin không hợp lệ
        }
    }

    private fun navigateToHomeFragment() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("shouldLaunchLoginActivity", true)
        startActivity(intent)
    }
}
