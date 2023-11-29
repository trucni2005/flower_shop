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
import com.example.sweetflowershop.ui.viewmodel.cart.CartViewModel
import com.example.sweetflowershop.data.repository.CartAPIService

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

        binding.btnCheckout.setOnClickListener {
            val intent = Intent(this, ChooseAddressActivity::class.java)
            startActivity(intent)
        }
    }
}