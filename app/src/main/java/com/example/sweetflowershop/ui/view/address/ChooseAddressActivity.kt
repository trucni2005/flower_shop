package com.example.sweetflowershop.ui.view.address

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.databinding.ActivityAddressListBinding
import com.example.sweetflowershop.data.repository.AddressRepository
import com.example.sweetflowershop.ui.adapter.AddressAdapter
import com.example.sweetflowershop.ui.viewmodel.AddressViewModel

class ChooseAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressListBinding
    private lateinit var addressAdapter: AddressAdapter
    private lateinit var viewModel: AddressViewModel
    private lateinit var apiService: AddressRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityAddressListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo ViewModel
        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)

        // Khởi tạo Adapter với danh sách rỗng ban đầu
        addressAdapter = AddressAdapter(ArrayList())

        // Khởi tạo API Service
        apiService = AddressRepository()

        // Thiết lập RecyclerView
        binding.rvAddressList.adapter = addressAdapter
        binding.rvAddressList.layoutManager = LinearLayoutManager(this)

        // Gọi hàm fetchAddressList của ViewModel
        viewModel.fetchAddressList(this)

        // Quan sát LiveData để cập nhật dữ liệu khi nó thay đổi
        viewModel.addressListLiveData.observe(this) { addresses ->
            addressAdapter.setData(addresses.toMutableList())
        }

        binding.tvAddAddress.setOnClickListener {
            val intent = Intent(this, AddAddressActivity::class.java)
            startActivity(intent)
        }
    }
}