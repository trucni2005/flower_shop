package com.example.sweetflowershop.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.data.model.review.Review
import com.example.sweetflowershop.databinding.ItemReviewBinding
import com.example.sweetflowershop.ui.viewmodel.ReviewViewModel
import com.squareup.picasso.Picasso

class ReviewAdapter(private val viewModel: ReviewViewModel) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private var reviews: List<Review> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewBinding.inflate(inflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review)
    }


    override fun getItemCount(): Int {
        return reviews.size
    }

    fun submitList(newReviews: List<Review>) {
        reviews = newReviews.take(5)
        Log.d("submitList", reviews.toString())
        notifyDataSetChanged()
    }


    inner class ReviewViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) {
            val review: Review? = reviews.getOrNull(adapterPosition)
            binding.review = review
            binding.executePendingBindings()

            review?.customer?.avatar?.let {
                Picasso.get().load(it).into(binding.ivItemImage)
            }


        }
    }
}
