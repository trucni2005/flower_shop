package com.example.sweetflowershop.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repository.ProductAPIService
import com.example.sweetflowershop.databinding.FlashSaleItemBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.view.product.ProductDetail
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.io.Serializable

class FlashSaleProductAdapter(private var flashSaleProducts: List<Product>) :
    RecyclerView.Adapter<FlashSaleProductAdapter.ViewHolder>() {

    private val productAPIService = ProductAPIService()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: FlashSaleItemBinding = FlashSaleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flashSaleProduct = flashSaleProducts[position]
        holder.bind(flashSaleProduct)
    }

    override fun getItemCount(): Int {
        return flashSaleProducts.size
    }

    inner class ViewHolder(private val binding: FlashSaleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val flashSaleProduct = flashSaleProducts[adapterPosition]
                val intent = Intent(binding.root.context, ProductDetail::class.java)
                intent.putExtra("product", flashSaleProduct as Serializable)
                binding.root.context.startActivity(intent)
            }
        }

        fun bind(flashSaleProduct: Product) {
            binding.tvPrice.text = "${flashSaleProduct.price}đ"
            val imageUrl =
                _Constant.baseUrl_ + "images/product/${flashSaleProduct.image1}"
            Picasso.get().load(imageUrl).into(binding.imageUrl)
        }
    }

    fun setData(newFlashSaleProducts: List<Product>) {
        flashSaleProducts = newFlashSaleProducts
        notifyDataSetChanged()
    }
}
