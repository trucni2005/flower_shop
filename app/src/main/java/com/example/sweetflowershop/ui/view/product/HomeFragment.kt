package com.example.sweetflowershop.ui.view.product

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.R
import com.example.sweetflowershop.databinding.FragmentHomeBinding
import com.example.sweetflowershop.ui.adapter.FlashSaleProductAdapter
import com.example.sweetflowershop.ui.adapter.ProductsAdapter
import com.example.sweetflowershop.ui.view.ChatActivity
import com.example.sweetflowershop.ui.view.cart.CartActivity
import com.example.sweetflowershop.ui.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var flashSaleProductsAdapter: FlashSaleProductAdapter
    private lateinit var bestSellerProductsAdapter: FlashSaleProductAdapter

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

        binding.btnMessage.setOnClickListener {
            val intent = Intent(requireContext(), ChatActivity::class.java)
            startActivity(intent)
        }

        binding.searchView.setOnClickListener{
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        productsAdapter = ProductsAdapter(emptyList())
        flashSaleProductsAdapter = FlashSaleProductAdapter(emptyList())
        bestSellerProductsAdapter = FlashSaleProductAdapter(emptyList())

        binding.rvProducts.adapter = productsAdapter
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvFlashsale.adapter = flashSaleProductsAdapter
        binding.rvFlashsale.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvBestseller.adapter = bestSellerProductsAdapter
        binding.rvBestseller.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.productsLiveData.observe(viewLifecycleOwner, Observer { products ->
            productsAdapter.setData(products)
        })

        viewModel.flashSaleProductsLiveData.observe(viewLifecycleOwner, Observer { flashSaleProducts ->
            flashSaleProductsAdapter.setData(flashSaleProducts)
        })

        viewModel.bestSellerProductsLiveData.observe(viewLifecycleOwner, Observer { bestSellerProducts ->
            bestSellerProductsAdapter.setData(bestSellerProducts)
        })

        viewModel.fetchProducts()
        viewModel.fetchFlashSaleProducts()
        viewModel.fetchBestSellerProducts()

        setupSlider()
    }

    private fun setupSlider() {
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
            Picasso.get().load(imageUrl).fit().into(imgView)
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
