package com.example.sweetflowershop.ui.view.login
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.sweetflowershop.databinding.LoginBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.view.forgotPassword.ForgotPasswordActivity
import com.example.sweetflowershop.ui.view.main.MainActivity
import com.example.sweetflowershop.ui.view.register.RegisterActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = LoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            btnLogin.setOnClickListener { tryLogin(etusername.text.toString(), etPassword.text.toString()) }
            btnRegister.setOnClickListener { startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) }
            tvForgotPass.setOnClickListener { startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java)) }
        }
    }

    private fun tryLogin(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            showToast("Vui lòng nhập tài khoản và mật khẩu!")
        } else {
            loginUser(username, password)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun loginUser(username: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {

            val sharedPreferences =
                getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("Authorization", "")
            val fcmToken = sharedPreferences.getString("FCM_TOKEN", "")


            Log.d("token", token ?: "Token is null or empty")
            Log.d("fcm_token", fcmToken ?: "FCM Token is null or empty")

            val response = sendLoginRequest(username, password, fcmToken)

            if (response.isSuccessful) {
                handleSuccessfulLogin(response, sharedPreferences)
            } else {
                Log.d("DEBUG", "Login request failed with code: ${response.code}")
            }
        }
    }

    private fun sendLoginRequest(username: String, password: String, fcmToken: String?): Response {
        val client = OkHttpClient()

        val jsonBody = JSONObject().apply {
            put("username", username)
            put("password", password)
            put("token", fcmToken)
        }

        val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())
        val fullUrl = "${_Constant.baseUrl}login-customer"

        val request = Request.Builder()
            .url(fullUrl)
            .post(requestBody)
            .build()

        return client.newCall(request).execute()
    }

    private fun handleSuccessfulLogin(response: Response, sharedPreferences: SharedPreferences) {
        val authorizationHeader = response.headers["Set-Cookie"]

        if (!authorizationHeader.isNullOrBlank()) {
            saveAuthorizationToken(authorizationHeader, sharedPreferences)
            runOnUiThread { launchMainActivity() }
        } else {
            runOnUiThread {
                showToast("Tài khoản hoặc mật khẩu bạn nhập chưa đúng!")
            }
        }
    }

    private fun saveAuthorizationToken(authorizationHeader: String, sharedPreferences: SharedPreferences) {
        sharedPreferences.edit { putString("Authorization", authorizationHeader) }
        Log.d("DEBUG", "Authorization saved: $authorizationHeader")
    }


    private fun launchMainActivity() {
        runOnUiThread {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.putExtra("shouldLaunchLoginActivity", false)
            startActivity(intent)
        }
    }
}

