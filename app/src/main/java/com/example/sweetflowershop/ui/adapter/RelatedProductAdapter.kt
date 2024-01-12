package com.example.sweetflowershop.ui.adapter
import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.data.repository.ProductRepository
import com.example.sweetflowershop.databinding.RelatedProductItemBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.view.product.ProductDetail
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.io.Serializable

class RelatedProductAdapter(private var relatedProducts: List<Product>) :
    RecyclerView.Adapter<RelatedProductAdapter.ViewHolder>() {

    private val productRepository = ProductRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RelatedProductItemBinding = RelatedProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val relatedProduct = relatedProducts[position]
        holder.bind(relatedProduct)
    }

    override fun getItemCount(): Int {
        return relatedProducts.size
    }

    inner class ViewHolder(private val binding: RelatedProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val relatedProduct = relatedProducts[adapterPosition]
                val intent = Intent(binding.root.context, ProductDetail::class.java)
                intent.putExtra("product", relatedProduct as Serializable)
                binding.root.context.startActivity(intent)
            }
        }

        fun bind(relatedProduct: Product) {
            binding.tvName.text = relatedProduct.name
            binding.tvPrice.text = "${relatedProduct.price}đ"

            val spannableString = SpannableString(binding.tvOriginalPrice.text)
            spannableString.setSpan(StrikethroughSpan(), 0, binding.tvOriginalPrice.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.tvOriginalPrice.text = spannableString

            val imageUrl = _Constant.baseUrl_ + "images/product/${relatedProduct.image1}"
            Picasso.get().load(imageUrl).into(binding.imageUrl)
        }
    }

    fun setData(newRelatedProducts: List<Product>) {
        relatedProducts = newRelatedProducts
        notifyDataSetChanged()
    }
}
