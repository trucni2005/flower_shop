package com.example.sweetflowershop.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.data.model.cart.CartToSend
import com.example.sweetflowershop.ui.viewmodel.cart.CartViewModel
import com.example.sweetflowershop.data.repository.CartAPIService
import com.example.sweetflowershop.network.apiService._Constant
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable


class CartAdapter(private var carts: MutableList<CartItem> = mutableListOf(),
                  private val cartViewModel: CartViewModel,
                  private val context: Context) :
    RecyclerView.Adapter<CartAdapter.Viewholder>() {

    private val APIService = CartAPIService()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val cartItem = carts[position]
        holder.bind(cartItem, position)
    }

    override fun getItemCount(): Int {
        return carts.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.findViewById(R.id.name)
        val tvItemPrice: TextView = itemView.findViewById(R.id.price)
        val tvItemQuantity: TextView = itemView.findViewById(R.id.quantity)
        val imageCart: ImageView = itemView.findViewById(R.id.cartImage)
        val tvItemOriginalPrice: TextView = itemView.findViewById(R.id.original_price)
        val add: ImageView = itemView.findViewById(R.id.plusLayout)
        val minus: ImageView = itemView.findViewById(R.id.minus)
        val editTextQuantity: EditText = itemView.findViewById(R.id.quantity)
        val buttonRemoveCart: ImageView = itemView.findViewById(R.id.button_remove_cart)

        fun bind(cartItem: CartItem, position: Int) {
            val productItem = cartItem.product
            tvItemName.text = productItem.name
            val spannableString =
                SpannableString("${productItem.original_price}")
            spannableString.setSpan(
                StrikethroughSpan(),
                0,
                spannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tvItemOriginalPrice.text = spannableString

            tvItemPrice.text = "${productItem.price}$"
            tvItemQuantity.text = "${cartItem.quantity}"

            var imageUrl = _Constant.baseUrl_ + "images/product/${productItem.image1}"
            Picasso.get().load(imageUrl).into(imageCart)
            cartViewModel.setTotalBill(carts)

            buttonRemoveCart.setOnClickListener {
                showDeleteConfirmationDialog(itemView, cartItem.id, position)
            }

            add.setOnClickListener {
                val currentQuantity = tvItemQuantity.text.toString().toInt()
                val newQuantity = currentQuantity + 1
                tvItemQuantity.text = "$newQuantity"
                updateCartItemQuantity(itemView, cartItem.id, newQuantity, position)
            }

            minus.setOnClickListener{
                val currentQuantity = tvItemQuantity.text.toString().toInt()
                val newQuantity = currentQuantity - 1
                if (newQuantity == 0) {
                        showDeleteConfirmationDialog(itemView, cartItem.id, position)
                    } else {
                        tvItemQuantity.text = "$newQuantity"
                        updateCartItemQuantity(itemView, cartItem.id, newQuantity, position)
                    }
            }


            editTextQuantity.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    val newQuantity = s.toString().toIntOrNull() ?: 0
                    updateCartItemQuantity(itemView, cartItem.id, newQuantity, position)
                }
            })
        }
    }

    private fun showDeleteConfirmationDialog(itemView: View, cartItemId: Int, position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(itemView.context)
        alertDialogBuilder.setTitle("Confirmation")
        alertDialogBuilder.setMessage("Do you want to remove this item from the cart?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            updateCartItemQuantity(itemView, cartItemId, 0, position)
            carts.removeAt(position)
            notifyDataSetChanged()
            cartViewModel.setTotalBill(carts)
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun updateCartItemQuantity(
        itemView: View,
        cartItemId: Int,
        newQuantity: Int,
        position: Int
    ) {
        val sharedPreferences =
            itemView.context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)
        Log.d("token11", token.toString())
        Log.d("cartItemId", cartItemId.toString())
        val cartToSend = CartToSend(cartItemId, newQuantity)
        if (token != null) {
            APIService.updateCartItemQuantity(token, cartToSend)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.let {
                    compositeDisposable.add(
                        it.subscribe(
                            { accountModel ->
                                if (accountModel.success) {
                                    val successMessage = accountModel.message
                                    Log.d("DEBUG", "Success. Message: $successMessage")
                                    carts[position].quantity = newQuantity
                                    cartViewModel.setTotalBill(carts)
                                } else {
                                    Log.e("ERROR", "Error: ${accountModel.message}")
                                }
                            },
                            { error ->
                                Log.e("ERROR", "Error: ${error.message}")
                            }
                        )
                    )
                }
        }
    }

    fun setData(newCarts: MutableList<CartItem>) {
        carts = newCarts
        notifyDataSetChanged()
    }

}