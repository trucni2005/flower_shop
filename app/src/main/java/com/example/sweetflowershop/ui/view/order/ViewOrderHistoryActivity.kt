package com.example.sweetflowershop.ui.view.order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.databinding.ActivityViewOrderBinding
import com.example.sweetflowershop.ui.adapter.OrderHistoryAdapter
import com.example.sweetflowershop.ui.viewmodel.OrderHistoryViewModel

class ViewOrderHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewOrderBinding
    private lateinit var orderHistoryAdapter: OrderHistoryAdapter
    private lateinit var orderHistoryViewModel: OrderHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityViewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo RecyclerView và Adapter
        orderHistoryAdapter = OrderHistoryAdapter(mutableListOf(), this)
        binding.rvViewOrder.layoutManager = LinearLayoutManager(this)
        binding.rvViewOrder.adapter = orderHistoryAdapter

        // Khởi tạo ViewModel
        orderHistoryViewModel = ViewModelProvider(this).get(OrderHistoryViewModel::class.java)

        // Quan sát LiveData từ ViewModel
        orderHistoryViewModel.orderHistoryLiveData.observe(this, { orderHistoryList ->
            orderHistoryAdapter.updateOrderHistoryList(orderHistoryList)
        })

        // Gọi phương thức để lấy danh sách đơn hàng lịch sử
        orderHistoryViewModel.fetchOrderHistory(this)
    }
}