package com.example.sweetflowershop.ui.view
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.data.model.order.OrderDetailHistory
import com.example.sweetflowershop.data.model.order.OrderHistory
import com.example.sweetflowershop.data.model.review.ReviewContent
import com.example.sweetflowershop.data.repository.ReviewRepository
import com.example.sweetflowershop.databinding.ActivityCreateReviewsBinding
import com.example.sweetflowershop.ui.adapter.CreateReviewAdapter
import com.example.sweetflowershop.ui.view.order.OrderSuccessFulActivity
import com.example.sweetflowershop.ui.view.order.ViewOrderHistoryActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CreateReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateReviewsBinding
    private lateinit var adapter: CreateReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Get the single OrderHistory object from the intent
        val orderHistoryItem = intent.getSerializableExtra("orderHistoryItem") as? OrderHistory

        // Check if orderHistoryItem is not null before proceeding
        orderHistoryItem?.let {
            it.orderDetailHistories?.let { orderDetailHistories ->
                Log.d("CreateReviewActivity", "OrderDetailHistories: ${orderDetailHistories.size}")
                setupCreateReviewAdapter(orderDetailHistories)
            }
        }

        binding.rvCreateReviews.layoutManager = LinearLayoutManager(this)

        binding.btnCheckout.setOnClickListener {
            val reviews = getReviewsFromAdapter()

            if (reviews.isNotEmpty()) {
                val sharedPreferences = this.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("Authorization", null)
                val reviewRepository = ReviewRepository()

                // Assuming your API endpoint is named createReviews and accepts a list of reviews
                if (token != null && orderHistoryItem != null) {
                    Log.d("CreateReviewActivity", "Token: $token")
                    Log.d("CreateReviewActivity", "OrderID: ${orderHistoryItem.id}")
                    Log.d("CreateReviewActivity", "Reviews: $reviews")

                    reviewRepository.createReviews(token, orderHistoryItem.id, reviews)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            // Handle the API response if needed
                            Log.d("CreateReviewActivity", "Reviews submitted successfully")
                            Toast.makeText(this, "Reviews submitted successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, ViewOrderHistoryActivity::class.java)
                            startActivity(intent)
                        }, {
                            // Handle errors
                            Log.e("CreateReviewActivity", "Failed to submit reviews. Error: ${it.message}")
                            Toast.makeText(this, "Failed to submit reviews", Toast.LENGTH_SHORT).show()
                        })
                }
            } else {
                Toast.makeText(this, "No reviews to submit", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupCreateReviewAdapter(orderDetailHistories: List<OrderDetailHistory>) {
        Log.d("CreateReviewActivity", "Setting up adapter. Number of items: ${orderDetailHistories.size}")
        adapter = CreateReviewAdapter(this, orderDetailHistories)
        binding.rvCreateReviews.adapter = adapter
    }

    private fun getReviewsFromAdapter(): List<ReviewContent> {
        val reviews = adapter.getReviews()
        Log.d("CreateReviewActivity", "Retrieved reviews from adapter: $reviews")
        return reviews
    }
}