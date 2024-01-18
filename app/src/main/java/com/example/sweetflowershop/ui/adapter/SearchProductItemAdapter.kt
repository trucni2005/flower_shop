package com.example.sweetflowershop.ui.adapter
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.databinding.SearchItemBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.view.product.ProductDetail
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.io.Serializable

class SearchProductItemAdapter(private var searchProducts: MutableList<Product>) :
    RecyclerView.Adapter<SearchProductItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: SearchItemBinding = SearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchProduct = searchProducts[position]
        holder.bind(searchProduct)
    }

    override fun getItemCount(): Int {
        return searchProducts.size
    }

    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val searchProduct = searchProducts[adapterPosition]
                val intent = Intent(binding.root.context, ProductDetail::class.java)
                intent.putExtra("product", searchProduct as Serializable)
                binding.root.context.startActivity(intent)
            }
        }

        fun bind(searchProduct: Product) {
            binding.tvName.text ="${searchProduct.name}đ"
            binding.tvPrice.text = "${searchProduct.price}đ"
            binding.tvDescription.text ="${searchProduct.description}đ"
            val imageUrl =
                _Constant.baseUrl_ + "images/product/${searchProduct.image1}"
            Picasso.get().load(imageUrl).into(binding.productImage)
        }
    }

    fun setData(newSearchProducts: MutableList<Product>) {
        Log.d("setDataAdapter", newSearchProducts.toString())
        searchProducts.clear()
        searchProducts.addAll(newSearchProducts)
        notifyDataSetChanged()
    }
}
