package com.example.sweetflowershop.ui.view.cart

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.ui.adapter.cart.CartAdapter
import com.example.sweetflowershop.databinding.FragmentCartsBinding
import com.example.sweetflowershop.ui.viewmodel.cart.CartViewModel
import com.example.sweetflowershop.viewmodel.CartAPIService

class CartActivity : AppCompatActivity() {
    private lateinit var binding: FragmentCartsBinding
    private lateinit var cartApiServices: CartAPIService
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCartsBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        cartApiServices = CartAPIService()
        cartAdapter = CartAdapter(ArrayList(), cartViewModel, this)

        binding.rvCartitems.adapter = cartAdapter
        binding.rvCartitems.layoutManager = LinearLayoutManager(this)

        cartViewModel.cartItemsLiveData.observe(this, Observer { carts ->
            cartAdapter.setData(carts.toMutableList())
        })

        cartViewModel.totalBillLiveData.observe(this, Observer { totalBill ->
            binding.tvTotalCart.text = "$totalBill"
        })
        cartViewModel.fetchCartItems(this)

    }
}