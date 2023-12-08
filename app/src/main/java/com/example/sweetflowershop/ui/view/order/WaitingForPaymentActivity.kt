package com.example.sweetflowershop.ui.view.order
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.databinding.WaitingForPaymentBinding
import com.example.sweetflowershop.ui.viewmodel.NotificationViewModel

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
