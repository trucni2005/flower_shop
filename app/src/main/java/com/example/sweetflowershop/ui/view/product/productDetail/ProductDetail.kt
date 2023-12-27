package com.example.sweetflowershop.ui.view.product.productDetail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.R
import com.example.sweetflowershop.databinding.ActivityProductDetailBinding
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.adapter.ReviewAdapter
import com.example.sweetflowershop.ui.viewmodel.ReviewViewModel
import com.squareup.picasso.Picasso

class ProductDetail : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product
    private lateinit var reviewViewModel: ReviewViewModel
    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        val viewRoot: View = binding.root
        setContentView(viewRoot)

        // Get product data from intent
        val bundle = intent.extras
        if (bundle != null) {
            product = bundle.getSerializable("product") as Product
        }

        // Initialize ViewModel
        reviewViewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)

        // Set product details to UI elements
        binding.tvProductDetailName.text = product.name.toString()
        binding.tvProductDetailPrice.text = product.price+"đ"
        binding.details.text = product.details
        binding.delivery.text = product.delivery
        binding.description.text = product.description

        // Set default rating if overall_rating is null
        binding.rating.rating = product.overall_rating?.toFloat() ?: 5.0f

        // Initialize ReviewAdapter and set it to the RecyclerView
        reviewAdapter = ReviewAdapter(reviewViewModel)
        binding.rvRating.adapter = reviewAdapter
        binding.rvRating.layoutManager = LinearLayoutManager(this)

        // Load images into ViewFlipper
        loadImagesIntoViewFlipper()

        // Load reviews for the product
        loadReviewsForProduct(product.id)
    }

    private fun loadReviewsForProduct(productId: Int) {
        // Observe reviewsLiveData from ReviewViewModel
        reviewViewModel.reviewsLiveData.observe(this, Observer { reviews ->
            reviewAdapter.submitList(reviews)
        })

        // Fetch reviews for the specified product ID
        reviewViewModel.fetchReviews(productId)
    }

    private fun loadImagesIntoViewFlipper() {
        val viewFlipper = binding.imgProductDetail
        viewFlipper.startFlipping()

        val imageUrls = listOf(
            product.image1,
            product.image2,
            product.image3,
            product.image4,
            product.image5
        )

        for (imageUrl in imageUrls) {
            if (imageUrl != null && imageUrl.isNotBlank()) {
                val imgView = ImageView(this)
                Picasso.get().load(_Constant.baseUrl_ + "images/product/${imageUrl}").into(imgView)
                imgView.scaleType = ImageView.ScaleType.FIT_XY
                viewFlipper.addView(imgView)
            }
        }

        viewFlipper.flipInterval = 3000
        viewFlipper.isAutoStart = true

        val slideIn = AnimationUtils.loadAnimation(this, R.anim.slider_in_right)
        val slideOut = AnimationUtils.loadAnimation(this, R.anim.slider_out_right)
        viewFlipper.inAnimation = slideIn
        viewFlipper.outAnimation = slideOut
    }
}
