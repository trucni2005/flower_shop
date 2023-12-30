package com.example.sweetflowershop.ui.view.order

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.databinding.ActivityOrderSuccessfulBinding
import com.example.sweetflowershop.ui.view.main.MainActivity

class OrderSuccessFulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSuccessfulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSuccessfulBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        Toast.makeText(this, "Order Successful!", Toast.LENGTH_SHORT).show()


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("shouldLaunchLoginActivity", false)
            startActivity(intent)
        }, 3000)
    }
}