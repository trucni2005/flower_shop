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

        val usernameEditText = binding.etUsername
        val emailEditText = binding.etEmail
        val passwordEditText = binding.etPassword
        val reenterPasswordEditText = binding.etRepass
        val phoneNumberEditText = binding.etPhone
        val submitButton = binding.btnSignUp

        submitButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val reenterPassword = reenterPasswordEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()

            if (password != reenterPassword) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
            } else {
                val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
                if (!Pattern.matches(emailPattern, email)) {
                    Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show()
                } else if (phoneNumber.length < 10) {
                    Toast.makeText(this, "Số điện thoại phải có ít nhất 10 số", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("DEBUG", "Gọi verify")
                    accountAPIService.verify_email(username, password, email, phoneNumber)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { accountModel ->
                                if (accountModel.success) {
                                    val successMessage = accountModel.message
                                    Log.d("DEBUG", "Đăng ký thành công. Message: $successMessage")
                                    val bundle = Bundle()
                                    bundle.putString("request", "0")
                                    bundle.putString("id", successMessage)
                                    bundle.putString("username", username)
                                    bundle.putString("password", password)
                                    bundle.putString("email", email)
                                    bundle.putString("phoneNumber", phoneNumber)
                                    val intent = Intent(this, VerifyByEmail::class.java)
                                    intent.putExtras(bundle)
                                    startActivity(intent)
                                } else {
                                    val errorMessage = accountModel.message
                                    Log.e("DEBUG", "Đăng ký không thành công. Message: $errorMessage")
                                }
                            },
                            { throwable ->
                                // Xử lý khi có lỗi xảy ra
                                Log.e("DEBUG", "Lỗi xảy ra: ${throwable.message}")
                                throwable.printStackTrace() // In lỗi ra màn hình
                            }
                        )

                }
            }
        }
    }
}
