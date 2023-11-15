package com.example.sweetflowershop.ui.view.product.productDetail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.R
import com.example.sweetflowershop.databinding.ActivityProductDetailBinding
import com.example.sweetflowershop.data.model.product.Product
import com.squareup.picasso.Picasso

class ProductDetail : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        val viewRoot: View = binding.root
        setContentView(viewRoot)

        val bundle = intent.extras

        if (bundle != null) {
            product = bundle.getSerializable("product") as Product
        }

        Log.d("Product", product.toString())
        binding.tvProductDetailName.text = product.name.toString()
        binding.tvProductDetailPrice.text = "$" + product.price
        binding.details.text = product.details
        binding.delivery.text = product.delivery
        binding.description.text = product.description
        if (product.overall_rating != null) {
            binding.rating.rating = product.overall_rating.toFloat()
        } else {
            binding.rating.rating = 5.0f
        }

        loadImagesIntoViewFlipper()
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
                Picasso.get().load("https://$imageUrl").into(imgView)
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
