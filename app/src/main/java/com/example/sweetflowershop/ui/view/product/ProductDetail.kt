package com.example.sweetflowershop.ui.view.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.R
import com.example.sweetflowershop.databinding.ActivityProductDetailBinding
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repository.ProductAPIService
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.adapter.ProductsAdapter
import com.example.sweetflowershop.ui.adapter.RelatedProductAdapter
import com.example.sweetflowershop.ui.adapter.ReviewAdapter
import com.example.sweetflowershop.ui.view.login.LoginActivity
import com.example.sweetflowershop.ui.viewmodel.HomeViewModel
import com.example.sweetflowershop.ui.viewmodel.ProductDetailViewModel
import com.example.sweetflowershop.ui.viewmodel.ReviewViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductDetail : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product
    private lateinit var reviewViewModel: ReviewViewModel
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var viewModel: ProductDetailViewModel
    private val productAPIService = ProductAPIService()
    private lateinit var relatedProductAdapter: RelatedProductAdapter
    private val compositeDisposable = CompositeDisposable()

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
        binding.tvProductDetailPrice.text = "${product.price}đ"
        binding.details.text = product.details
        binding.delivery.text = product.delivery
        binding.description.text = product.description

        // Set default rating if overall_rating is null
        binding.rating.rating = product.overall_rating?.toFloat() ?: 5.0f

        // Initialize ReviewAdapter and set it to the RecyclerView
        reviewAdapter = ReviewAdapter(reviewViewModel)
        binding.rvRating.adapter = reviewAdapter
        binding.rvRating.layoutManager = LinearLayoutManager(this)

        relatedProductAdapter = RelatedProductAdapter(emptyList())

        binding.RecommendRcv.adapter = relatedProductAdapter
        binding.RecommendRcv.layoutManager = GridLayoutManager(this, 2)

        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        viewModel.relatedProductsLiveData.observe(this, Observer { products ->
            relatedProductAdapter.setData(products)
        })

        viewModel.fetchRelatedProducts(product.id)

        binding.addToCartProductDetailsPage.setOnClickListener {
            val productId = product.id
            val sharedPreferences =
                this.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("Authorization", null)
            Log.d("token", token.toString())

            if (!token.isNullOrEmpty()) {
                val addToCartObservable = productAPIService.addToCart(token, productId)

                addToCartObservable
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                        { accountModel ->
                            if (accountModel.success) {
                                Log.d("Test", "Success")
                            } else {
                                Log.e("Test", "Failed: ${accountModel.message}")
                            }
                        },
                        { error ->
                            Log.e("Test", "Error: $error")
                        }
                    )?.let {
                        compositeDisposable.add(it)
                    }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

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

    override fun onDestroy() {
        super.onDestroy()
        // Dispose of all the subscriptions to avoid memory leaks
        compositeDisposable.clear()
    }
}
