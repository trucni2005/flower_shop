package com.example.sweetflowershop.ui.adapter

import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.ui.view.product.productDetail.ProductDetail
import com.example.sweetflowershop.data.repository.ProductAPIService
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.view.login.LoginActivity
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.Serializable

class ProductsAdapter(private var products: List<Product>) :

    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private val productAPIService = ProductAPIService()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageUrl: ImageView = itemView.findViewById(R.id.image_url)
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvOriginalPrice: TextView = itemView.findViewById(R.id.tv_original_price)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        private val btnCart: Button = itemView.findViewById(R.id.btn_add_cart)

        init {
            itemView.setOnClickListener {
                val product = products[adapterPosition]
                val intent = Intent(itemView.context, ProductDetail::class.java)
                intent.putExtra("product", product as Serializable)
                itemView.context.startActivity(intent)
            }

            btnCart.setOnClickListener {
                val productId = products[adapterPosition].id
                val sharedPreferences =
                    itemView.context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("Authorization", null)
                Log.d("token", token.toString())

                if (!token.isNullOrEmpty()) {
                    val addToCartObservable = productAPIService.addToCart(token, productId)

                    addToCartObservable
                        ?.subscribeOn(Schedulers.io())
                        ?.observeOn(AndroidSchedulers.mainThread())
                        ?.subscribe(
                            { accountModel ->
                                if (accountModel.success) {
                                    Log.d("Test", "Success")
                                } else {
                                    Log.e("Test", "Failed: ${accountModel.message}")
                                }
                            },
                            { error ->
                                Log.e("Test", "Error: $error")
                            }
                        )?.let {
                            compositeDisposable.add(it)
                        }
                } else {
                    val intent = Intent(itemView.context, LoginActivity::class.java)
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun bind(product: Product) {
            tvName.text = product.name
            tvOriginalPrice.text = product.original_price
            tvPrice.text = "${product.price}$"
            val spannableString = SpannableString(tvOriginalPrice.text)
            spannableString.setSpan(StrikethroughSpan(), 0, tvOriginalPrice.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvOriginalPrice.text = spannableString

            val image_url = _Constant.baseUrl_ + "images/product/${product.image1}"
            Picasso.get().load(image_url).into(imageUrl)
        }
    }

    fun setData(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}