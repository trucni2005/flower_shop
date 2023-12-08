package com.example.sweetflowershop.ui.view.order
import OrderHistoryDetailAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.data.model.order.OrderHistory
import com.example.sweetflowershop.databinding.ActivityCheckoutBinding
import com.example.sweetflowershop.databinding.ActivityMyOrderDetailsBinding

class OrderDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyOrderDetailsBinding
    private lateinit var orderDetailAdapter: OrderHistoryDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val orderHistoryItem = intent.getSerializableExtra("orderHistoryItem") as? OrderHistory
        if (orderHistoryItem != null) {
            binding.tvOrderDetailsId.text= orderHistoryItem.id.toString()
            binding.tvOrderDetailsDate.text = orderHistoryItem.orderDateTime
            when (orderHistoryItem.orderStatus) {
                "WAITING" -> binding.tvOrderStatus.text = "Đang chờ xác nhận"
                "CONFIRMED" -> binding.tvOrderStatus.text = "Đơn hàng đã xác nhận"
                "SENT" -> binding.tvOrderStatus.text = "Đang giao hàng"
                "RECEIVED" -> binding.tvOrderStatus.text = "Đã giao hàng"
                "CANCELLED" -> binding.tvOrderStatus.text = "Đã huỷ"
                "REJECT" -> binding.tvOrderStatus.text = "Đã từ chối"
                else -> binding.tvOrderStatus.text = "Trạng thái không xác định"
            }
            binding.tvMyOrderDetailsAddress.text = orderHistoryItem.address
            binding.tvMyOrderDetailsFullName.text = orderHistoryItem.nameCustomerReceive
            binding.tvMyOrderDetailsMobileNumber.text = orderHistoryItem.phoneCustomerReceive
            binding.tvOrderDetailsSubTotal.text = orderHistoryItem.totalPrice.toString()+"đ"
            binding.tvOrderDetailsShippingCharge.text = orderHistoryItem.shipPrice.toString()+"đ"
            binding.tvDiscount.text = "-"+ orderHistoryItem.discount.toString()+"đ"
            binding.tvOrderDetailsTotalAmount.text = orderHistoryItem.amount.toString()+"đ"
            orderDetailAdapter = OrderHistoryDetailAdapter(orderHistoryItem.orderDetailHistories ?: emptyList())
            binding.rvMyOrderItemsList.layoutManager = LinearLayoutManager(this)
            binding.rvMyOrderItemsList.adapter = orderDetailAdapter
            if (orderHistoryItem.paymentOnline)
                binding.tvPaymentMethods.text = "Payment Online"
        }
    }
}