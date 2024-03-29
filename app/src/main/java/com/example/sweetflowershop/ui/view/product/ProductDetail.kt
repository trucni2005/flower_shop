package com.example.sweetflowershop.ui.view.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.customer_account.AccountModel
import com.example.sweetflowershop.databinding.ActivityProductDetailBinding
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repository.ProductRepository
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.adapter.RelatedProductAdapter
import com.example.sweetflowershop.ui.adapter.ReviewAdapter
import com.example.sweetflowershop.ui.view.login.LoginActivity
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
    private val productRepository = ProductRepository()
    private lateinit var relatedProductAdapter: RelatedProductAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            product = bundle.getSerializable("product") as Product
        }

        reviewViewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
        reviewAdapter = ReviewAdapter(reviewViewModel)
        relatedProductAdapter = RelatedProductAdapter(emptyList())

        setupUI()
        setupReviewAdapter()
        setupRelatedProductAdapter()

        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        viewModel.relatedProductsLiveData.observe(this, { products ->
            relatedProductAdapter.setData(products)
        })

        viewModel.fetchRelatedProducts(product.id)

        binding.addToCartProductDetailsPage.setOnClickListener {
            handleAddToCart()
        }

        loadImagesIntoViewFlipper()
        loadReviewsForProduct(product.id)
    }

    private fun setupUI() {
        binding.tvProductDetailName.text = product.name
        binding.tvProductDetailPrice.text = "${product.price}đ"
        binding.details.text = product.details
        binding.delivery.text = product.delivery
        binding.description.text = product.description
        binding.rating.rating = product.overall_rating?.toFloat() ?: 5.0f
    }

    private fun setupReviewAdapter() {
        reviewAdapter = ReviewAdapter(reviewViewModel)
        binding.rvRating.adapter = reviewAdapter
        binding.rvRating.layoutManager = LinearLayoutManager(this)
    }

    private fun setupRelatedProductAdapter() {
        relatedProductAdapter = RelatedProductAdapter(emptyList())
        binding.RecommendRcv.adapter = relatedProductAdapter
        binding.RecommendRcv.layoutManager = GridLayoutManager(this, 2)
    }

    private fun handleAddToCart() {
        val productId = product.id
        val sharedPreferences = this.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)

        if (!token.isNullOrEmpty()) {
            val addToCartObservable = productRepository.addToCart(token, productId)

            addToCartObservable
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                    { accountModel ->
                        handleAddToCartSuccess(accountModel)
                    },
                    { error ->
                        handleAddToCartError(error)
                    }
                )?.let {
                    compositeDisposable.add(it)
                }
        } else {
            navigateToLogin()
        }
    }

    private fun handleAddToCartSuccess(accountModel: AccountModel) {
        if (accountModel.success) {
            Toast.makeText(this, "Thêm sản phẩm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show()
        } else {
            Log.e("Test", "Failed: ${accountModel.message}")
        }
    }

    private fun handleAddToCartError(error: Throwable) {
        Log.e("Test", "Error: $error")
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun loadReviewsForProduct(productId: Int) {
        reviewViewModel.reviewsLiveData.observe(this, Observer { reviews ->
            reviewAdapter.submitList(reviews)
        })

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
        compositeDisposable.clear()
    }
}
