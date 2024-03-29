package com.example.sweetflowershop.ui.view.forgotPassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.ui.view.main.MainActivity
import com.example.sweetflowershop.databinding.ActivityInputNewPassBinding
import com.example.sweetflowershop.data.repository.AccountRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class InputNewPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputNewPassBinding
    private val accountAPIService = AccountRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityInputNewPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val username = intent.getStringExtra("username")
            val password = binding.etReenterPassword.text.toString()

            username?.let {
                resetPassword(username, password)
            }
        }
    }

    private fun resetPassword(username: String, password: String) {
        Log.d("DEBUG", username)

        accountAPIService.resetPassword(username, password)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.let { disposable ->
                compositeDisposable.add(
                    disposable.subscribe { accountModel ->
                        if (accountModel.success) {
                            Log.d("DEBUG", "Reset password success. Please login your account!")
                            showToast("Thay đổi mật khẩu thành công! Vui lòng đăng nhập lại bằng mật khẩu mới")
                            navigateToLoginActivity()
                        }
                    }
                )
            }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("shouldLaunchLoginActivity", true)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
