package com.example.sweetflowershop.ui.view.product

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
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

        setClickListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()
        initializeAdapters()
        setupRecyclerViews()
        observeLiveData()
        fetchData()
        setupSlider()
    }

    private fun setClickListeners() {
        binding.btnCart.setOnClickListener {
            navigateToCart()
        }

        binding.btnMessage.setOnClickListener {
            navigateToChat()
        }

        binding.searchView.setOnClickListener {
            navigateToSearch()
        }
    }

    private fun navigateToCart() {
        val intent = Intent(requireContext(), CartActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToChat() {
        val intent = Intent(requireContext(), ChatActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSearch() {
        val intent = Intent(requireContext(), SearchActivity::class.java)
        startActivity(intent)
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private fun initializeAdapters() {
        productsAdapter = ProductsAdapter(emptyList())
        flashSaleProductsAdapter = FlashSaleProductAdapter(emptyList())
        bestSellerProductsAdapter = FlashSaleProductAdapter(emptyList())
    }

    private fun setupRecyclerViews() {
        binding.rvProducts.adapter = productsAdapter
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvFlashsale.adapter = flashSaleProductsAdapter
        binding.rvFlashsale.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvBestseller.adapter = bestSellerProductsAdapter
        binding.rvBestseller.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observeLiveData() {
        viewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            productsAdapter.setData(products)
        }

        viewModel.flashSaleProductsLiveData.observe(viewLifecycleOwner) { flashSaleProducts ->
            flashSaleProductsAdapter.setData(flashSaleProducts)
        }

        viewModel.bestSellerProductsLiveData.observe(viewLifecycleOwner) { bestSellerProducts ->
            bestSellerProductsAdapter.setData(bestSellerProducts)
        }
    }

    private fun fetchData() {
        viewModel.fetchProducts()
        viewModel.fetchFlashSaleProducts()
        viewModel.fetchBestSellerProducts()
    }

    private fun setupSlider() {
        val viewFlipper = binding.vfSlider
        viewFlipper.startFlipping()

        val imageResourceIds = arrayOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
        )

        for (imageResourceId in imageResourceIds) {
            val imgView = ImageView(requireContext())
            imgView.setImageResource(imageResourceId)
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
