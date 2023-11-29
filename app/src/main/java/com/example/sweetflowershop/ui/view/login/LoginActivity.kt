package com.example.sweetflowershop.ui.view.login
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.databinding.LoginBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.data.repository.AccountRepository
import com.example.sweetflowershop.ui.view.forgotPassword.ForgotPasswordActivity
import com.example.sweetflowershop.ui.view.main.MainActivity
import com.example.sweetflowershop.ui.view.register.RegisterActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginBinding
    private val accountAPIService = AccountRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = LoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener {
            val username = binding.etusername.text.toString()
            val password = binding.etPassword.text.toString()

            checkLogin(username, password)
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.tvForgotPass.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkLogin(username: String, password: String) {
        if (username.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter your username!", Toast.LENGTH_SHORT)
                .show()
        } else if (password.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter your password!", Toast.LENGTH_SHORT)
                .show()
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
                val client = OkHttpClient()

                val jsonBody = JSONObject()
                jsonBody.put("username", username)
                jsonBody.put("password", password)

                val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())

                val fullUrl = _Constant.baseUrl + "login-customer"

                val request = Request.Builder()
                    .url(fullUrl)
                    .post(requestBody)
                    .build()

                val call = client.newCall(request)
                val response = call.execute()

                if (response.isSuccessful) {
                    val headers = response.headers
                    val authorizationHeader = headers["Set-Cookie"]

                    if (authorizationHeader != null) {
                        val editor = sharedPreferences.edit()
                        editor.putString("Authorization", authorizationHeader)
                        editor.apply()
                        Log.d("DEBUG", "Authorization saved: $authorizationHeader")
                        runOnUiThread {
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("shouldLaunchLoginActivity", false)
                            startActivity(intent)
                        }
                    }
                    else {
                        Log.d("DEBUG", "Inside else block");
                    }
                }
            }
        }
    }
}

