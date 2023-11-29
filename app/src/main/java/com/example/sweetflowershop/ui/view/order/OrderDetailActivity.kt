package com.example.sweetflowershop.ui.view.order

import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.databinding.ActivityMyOrderDetailsBinding
import com.example.sweetflowershop.data.repository.CartAPIService

class OrderDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyOrderDetailsBinding
    private lateinit var cartApiServices: CartAPIService

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMyOrderDetailsBinding.inflate(layoutInflater)
//        supportActionBar?.hide()
//        setContentView(binding.root)
//
//        orderDetailViewModel = ViewModelProvider(this).get(OrderDetailViewModel::class.java)
//
//        cartApiServices = CartAPIService()
//        orderDetailAdapter = OrderDetailAdapter(ArrayList(), orderDetailViewModel, this)
//
//        binding.rvOrderItems.adapter = orderDetailAdapter
//        binding.rvOrderItems.layoutManager = LinearLayoutManager(this)
//
//        // Lấy địa chỉ từ Intent
//        val address = intent.getSerializableExtra("address") as YourAddressClass
//
//        // Bây giờ bạn có thể sử dụng đối tượng 'address' trong hoạt động của mình theo cách cần thiết
//        // Ví dụ, bạn có thể đặt nó vào TextView nếu bạn có một TextView hiển thị địa chỉ trong bố cục của mình
//        binding.tvShippingAddress.text = address.fullAddress // thay 'fullAddress' bằng trường thực tế trong đối tượng địa chỉ của bạn
//
//        // Giả sử bạn có một phương thức để lấy danh sách sản phẩm đơn hàng dựa trên địa chỉ, thực hiện nó trong ViewModel của bạn
//        orderDetailViewModel.fetchOrderItems(address)
//
//        orderDetailViewModel.orderItemsLiveData.observe(this, Observer { orderItems ->
//            orderDetailAdapter.setData(orderItems.toMutableList())
//        })
//
//        // Logic khác cho OrderDetailActivity của bạn
//    }
}