package com.example.sweetflowershop.ui.adapter.category

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.category.Category
import com.example.sweetflowershop.ui.view.product.productsByCategory.ProductsByCategory
import com.squareup.picasso.Picasso

class CategoryAdapter(private var categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.tvName.text = category.name
        holder.description.text = category.detail
        Picasso.get().load(category.image).into(holder.imageUrl)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductsByCategory::class.java)
            intent.putExtra("categoryId", category.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val imageUrl: ImageView = itemView.findViewById(R.id.image_url)
        val description: TextView = itemView.findViewById(R.id.tv_detail)
    }

    fun updateCategories(newCategories: List<Category>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}
