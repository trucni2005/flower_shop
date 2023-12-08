package com.example.sweetflowershop.ui.view.product.productList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sweetflowershop.R
import com.example.sweetflowershop.databinding.FragmentHomeBinding
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.sweetflowershop.ui.adapter.ProductsAdapter
import com.squareup.picasso.Picasso
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.sweetflowershop.ui.view.cart.CartActivity
import com.example.sweetflowershop.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.btnCart.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        productsAdapter = ProductsAdapter(emptyList()) // Sử dụng danh sách rỗng ban đầu

        binding.rvProducts.adapter = productsAdapter
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.productsLiveData.observe(viewLifecycleOwner, Observer { products ->
            productsAdapter.setData(products)
        })

        viewModel.fetchProducts()

        ActionSlider()
    }
    private fun ActionSlider() {
        val viewFlipper = binding.vfSlider
        viewFlipper.startFlipping()

        val imageUrls = listOf(
            "https://assets.flowerstore.ph/public/tenantPH/app/assets/images/banner/747_PRoA5mYfuRnpS2AKnUfJpndMw.webp",
            "https://cdn.euroflorist.com/cmspr/Uk/2016widepromo26.webp",
            "https://assets.flowerstore.ph/public/tenantPH/app/assets/images/banner/747_Gao72BRYYsDbvFMm2llsBr13Y.webp",
            "https://cdn.euroflorist.com/cmspr/Uk/2016widepromo26.webp",
            "https://assets.flowerstore.ph/public/tenantPH/app/assets/images/banner/747_TES1lgnM2Ynzy0cNb8IdYFU5R.webp",
            "https://cdn.euroflorist.com/cmspr/Uk/catpromo2018_plantswide0.webp"
        )

        for (imageUrl in imageUrls) {
            val imgView = ImageView(requireContext())
            Picasso.get().load(imageUrl).into(imgView)
            imgView.scaleType = ImageView.ScaleType.FIT_XY
            viewFlipper.addView(imgView)
        }

        viewFlipper.flipInterval = 3000
        viewFlipper.isAutoStart = true

        val slideIn = AnimationUtils.loadAnimation(requireContext(), R.anim.slider_in_right)
        val slideOut = AnimationUtils.loadAnimation(requireContext(), R.anim.slider_out_right)
        viewFlipper.inAnimation = slideIn
        viewFlipper.outAnimation = slideOut
    }

}
