package com.example.sweetflowershop.ui.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.data.model.order.OrderDetailHistory
import com.example.sweetflowershop.data.model.review.ReviewContent
import com.example.sweetflowershop.databinding.ItemCreateReviewBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.squareup.picasso.Picasso

class CreateReviewAdapter(
    private val context: Context,
    private val orderDetailHistoryList: List<OrderDetailHistory>
) : RecyclerView.Adapter<CreateReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCreateReviewBinding = ItemCreateReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderDetail = orderDetailHistoryList[position]
        holder.bind(orderDetail)
    }

    override fun getItemCount(): Int {
        return orderDetailHistoryList.size
    }

    fun updateRating(position: Int, rating: Int) {
        if (position in orderDetailHistoryList.indices) {
            orderDetailHistoryList[position].rating = rating.toInt()
            Log.d("CreateReviewAdapter", "Updated rating at position $position: $rating")
        }
    }

    fun updateContent(position: Int, content: String) {
        if (position in orderDetailHistoryList.indices) {
            orderDetailHistoryList[position].content = content
            Log.d("CreateReviewAdapter", "Updated content at position $position: $content")
        }
    }

    fun getReviews(): List<ReviewContent> {
        val reviews = orderDetailHistoryList.map { ReviewContent(it.productId, it.rating, it.content) }
        Log.d("CreateReviewAdapter", "Retrieved reviews: $reviews")
        return reviews
    }

    inner class ViewHolder(val binding: ItemCreateReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(orderDetail: OrderDetailHistory) {
            binding.tvName.text = orderDetail.nameProduct
            binding.tvItemPrice.text = orderDetail.priceProduct.toString()
            val imageUrl = _Constant.baseUrl_ + "images/product/${orderDetail.image}"
            Picasso.get().load(imageUrl).into(binding.imageUrl)

            binding.tvItemQuantity.text = "Số lượng: " + orderDetail.quantity
            binding.ratingReview.rating = orderDetail.rating.toFloat()

            binding.ratingReview.setOnRatingBarChangeListener { _, rating, fromUser ->
                if (fromUser) {
                    val roundedRating = rating.toInt()
                    updateRating(adapterPosition, roundedRating)
                }
            }

            binding.contentReview.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    updateContent(adapterPosition, s.toString())
                }
            })
        }
    }
}