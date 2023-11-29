package com.example.sweetflowershop.ui.view.order

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.databinding.ActivityOrderSuccessfulBinding

class OrderSuccessFulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSuccessfulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSuccessfulBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
    }
}