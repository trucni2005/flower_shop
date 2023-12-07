package com.example.sweetflowershop.ui.view.order
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sweetflowershop.databinding.WaitingForPaymentBinding
import com.example.sweetflowershop.ui.viewmodel.notification.NotificationViewModel

class WaitingForPaymentActivity : AppCompatActivity() {
    private lateinit var binding: WaitingForPaymentBinding
    private lateinit var notificationViewModel: NotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("WaitingForPaymentActivity", "onCreate is called")
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = WaitingForPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
