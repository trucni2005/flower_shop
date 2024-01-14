package com.example.sweetflowershop.ui.view.cart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.ui.adapter.CartAdapter
import com.example.sweetflowershop.databinding.FragmentCartsBinding
import com.example.sweetflowershop.ui.view.address.ChooseAddressActivity
import com.example.sweetflowershop.ui.viewmodel.CartViewModel
import com.example.sweetflowershop.data.repository.CartRepository
class CartActivity : AppCompatActivity() {

    private lateinit var binding: FragmentCartsBinding
    private lateinit var cartApiServices: CartRepository
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCartsBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
        observeViewModel()

        binding.btnCheckout.setOnClickListener {
            navigateToChooseAddress()
        }
    }

    private fun setupViewModel() {
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        cartApiServices = CartRepository()
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(ArrayList(), cartViewModel, this)
        binding.rvCartitems.adapter = cartAdapter
        binding.rvCartitems.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        cartViewModel.cartItemsLiveData.observe(this) { carts ->
            cartAdapter.setData(carts.toMutableList())
        }

        cartViewModel.totalBillLiveData.observe(this) { totalBill ->
            val formattedTotalBill = totalBill.toInt().toString() + "đ"
            binding.tvTotalCart.text = formattedTotalBill
        }

        cartViewModel.fetchCartItems(this)
    }

    private fun navigateToChooseAddress() {
        val intent = Intent(this, ChooseAddressActivity::class.java)
        startActivity(intent)
    }
}

