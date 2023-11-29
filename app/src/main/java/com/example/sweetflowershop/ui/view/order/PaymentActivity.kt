package com.example.sweetflowershop.ui.view.order

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sweetflowershop.R
import com.example.sweetflowershop.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment)

        binding.cashPayment.setOnClickListener {
            handlePaymentSelection("cash")
        }

        binding.onlinePayment.setOnClickListener {
            handlePaymentSelection("online")
        }
    }

    private fun handlePaymentSelection(paymentMethod: String) {
        when (paymentMethod) {
            "cash" -> {
                setResultAndFinish("cash")
            }
            "online" -> {
                setResultAndFinish("online")
            }
        }
    }

    private fun setResultAndFinish(selectedPaymentMethod: String) {
        val resultIntent = Intent()
        resultIntent.putExtra("selectedPaymentMethod", selectedPaymentMethod)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
