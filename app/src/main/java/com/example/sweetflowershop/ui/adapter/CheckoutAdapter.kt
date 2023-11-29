package com.example.sweetflowershop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.databinding.ItemListLayoutBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.squareup.picasso.Picasso

class CheckoutAdapter(
    private var checkoutItems: MutableList<CartItem> = mutableListOf(),
    private val context: Context
) : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checkoutItem = checkoutItems[position]
        holder.bind(checkoutItem)
    }

    override fun getItemCount(): Int {
        return checkoutItems.size
    }

    inner class ViewHolder(private val binding: ItemListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(checkoutItem: CartItem) {
            binding.item = checkoutItem
            val url = _Constant.baseUrl_ + "images/product/${checkoutItem.product.image1}"
            Picasso.get().load(url).into(binding.ivItemImage)
            binding.tvItemName.text = checkoutItem.product.name
            binding.tvItemPrice.text = checkoutItem.product.price
            binding.tvItemQuantity.text = "Số lượng:" + checkoutItem.quantity
        }
    }

    fun updateCheckoutItems(newCheckoutItems: MutableList<CartItem>) {
        checkoutItems.clear()
        checkoutItems.addAll(newCheckoutItems)
        notifyDataSetChanged()
    }
}