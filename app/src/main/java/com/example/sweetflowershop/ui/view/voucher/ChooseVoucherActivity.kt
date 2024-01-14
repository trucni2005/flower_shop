package com.example.sweetflowershop.ui.view.voucher

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.data.model.voucher.Voucher
import com.example.sweetflowershop.databinding.ActivityChooseVoucherBinding
import com.example.sweetflowershop.ui.adapter.VoucherAdapter

class ChooseVoucherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseVoucherBinding
    private lateinit var adapter: VoucherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseVoucherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val vouchers = intent.getSerializableExtra("voucherList") as? ArrayList<Voucher>

        setupVoucherAdapter(vouchers)
    }

    private fun setupVoucherAdapter(vouchers: ArrayList<Voucher>?) {
        if (vouchers.isNullOrEmpty()) {
            println("No vouchers available or the voucher list is empty.")
            return
        }

        val onItemClick: (Voucher) -> Unit = { voucher ->
            handleVoucherItemClick(voucher)
        }

        adapter = VoucherAdapter(vouchers, onItemClick)
        binding.rvVouchers.layoutManager = LinearLayoutManager(this)
        binding.rvVouchers.adapter = adapter
    }

    private fun handleVoucherItemClick(voucher: Voucher) {
        Log.d("Clicked voucher ID", voucher.id.toString())
        val resultIntent = Intent()
        resultIntent.putExtra("selectedVoucherCode", voucher.code)
        resultIntent.putExtra("selectedVoucherId", voucher.id)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
