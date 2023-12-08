package com.example.sweetflowershop.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.order.OrderHistory
import com.example.sweetflowershop.data.model.order.OrderStatus
import com.example.sweetflowershop.data.model.voucher.Voucher
import com.example.sweetflowershop.databinding.ItemOrderBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.view.order.CheckoutActivity
import com.example.sweetflowershop.ui.view.order.OrderDetailActivity
import com.example.sweetflowershop.ui.view.voucher.ChooseVoucherActivity
import com.squareup.picasso.Picasso

class OrderHistoryAdapter(
    private var orderHistoryList: MutableList<OrderHistory> = mutableListOf(),
    private val context: Context
) : RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemOrderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_order,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderHistoryItem = orderHistoryList[position]
        holder.bind(orderHistoryItem)
    }

    override fun getItemCount(): Int {
        return orderHistoryList.size
    }

    inner class ViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(orderHistoryItem: OrderHistory) {
            val product1= orderHistoryItem.orderDetailHistories?.get(0)
            val image_product1 = product1?.image
            val url = _Constant.baseUrl_ + "images/product/${image_product1}"
            Picasso.get().load(url).into(binding.imageUrl)
            binding.idOrder.text = "Order ID:" + orderHistoryItem.id.toString()
            binding.tvDateOrder.text = orderHistoryItem.orderDateTime
            if (product1 != null) {
                binding.tvName.text = product1.nameProduct
                binding.tvItemPrice.text = product1.priceProduct.toString()+"đ"
                binding.tvItemQuantity.text = "Số lượng: " + product1.quantity
            }

            when (orderHistoryItem.orderStatus) {
                "WAITING" -> binding.idStatus.text = "Đang chờ xác nhận"
                "CONFIRMED" -> binding.idStatus.text = "Đơn hàng đã xác nhận"
                "SENT" -> binding.idStatus.text = "Đang giao hàng"
                "RECEIVED" -> {
                    binding.idStatus.text = "Đã giao hàng"
                    binding.button.text = "Đánh giá"
                }
                "CANCELLED" -> {
                    binding.idStatus.text = "Đã huỷ"
                    binding.button.text = "Mua lại"
                }
                "REJECT" -> binding.idStatus.text = "Đã từ chối"
                else -> binding.idStatus.text = "Trạng thái không xác định"
            }

            binding.cvOrder.setOnClickListener {
                val intent = Intent(context, OrderDetailActivity::class.java)
                intent.putExtra("orderHistoryItem", orderHistoryItem)
                context.startActivity(intent)
            }
        }
    }

    fun updateOrderHistoryList(newOrderHistoryList: List<OrderHistory>) {
        orderHistoryList.clear()
        orderHistoryList.addAll(newOrderHistoryList)
        notifyDataSetChanged()
    }
}
