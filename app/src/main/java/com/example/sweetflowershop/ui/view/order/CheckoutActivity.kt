package com.example.sweetflowershop.ui.view.order
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.data.model.order.Order
import com.example.sweetflowershop.data.model.voucher.Voucher
import com.example.sweetflowershop.databinding.ActivityCheckoutBinding
import com.example.sweetflowershop.ui.adapter.CheckoutAdapter
import com.example.sweetflowershop.ui.view.voucher.ChooseVoucherActivity
import com.example.sweetflowershop.ui.viewmodel.OrderViewModel

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var checkoutAdapter: CheckoutAdapter
    private lateinit var orderViewModel: OrderViewModel
    private var addressId: Int = -1
    private var order: Order? = null
    private var voucherId: Long = -1

    companion object {
        const val CHOOSE_VOUCHER_REQUEST_CODE = 101
        const val PAYMENT_REQUEST_CODE = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupViews()
        handleAddressData()
        setupListeners()
    }

    private fun setupViews() {
        checkoutAdapter = CheckoutAdapter(context = this)
        binding.rvCartListItems.layoutManager = LinearLayoutManager(this)
        binding.rvCartListItems.adapter = checkoutAdapter
    }

    private fun handleAddressData() {
        val address = intent.getSerializableExtra("address") as? Address
        addressId = address?.id?.toInt() ?: -1

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        binding.orderViewModel = orderViewModel

        if (address != null) {
            val formattedAddress =
                "${address.street}, ${address.ward}, ${address.district}, ${address.city}"
            binding.tvCheckoutFullName.text = address.nameCustomer
            binding.tvCheckoutMobileNumber.text = address.phoneNumber
            binding.tvCheckoutAddress.text = formattedAddress
        }

        updateOrder(addressId, null, false, null)
    }

    private fun setupListeners() {
        binding.tvPaymentMode.setOnClickListener { startPaymentActivity() }
        binding.etVoucher.setOnClickListener { startChooseVoucherActivity() }
        binding.btnPlaceOrder.setOnClickListener { handlePlaceOrder() }
    }

    private fun startPaymentActivity() {
        val intent = Intent(this, PaymentActivity::class.java)
        startActivityForResult(intent, PAYMENT_REQUEST_CODE)
    }

    private fun startChooseVoucherActivity() {
        val voucherList: List<Voucher> = order?.vouchers ?: emptyList()
        val intent = Intent(this, ChooseVoucherActivity::class.java)
        intent.putExtra("voucherList", ArrayList(voucherList))
        startActivityForResult(intent, CHOOSE_VOUCHER_REQUEST_CODE)
    }

    private fun handlePlaceOrder() {
        val note: String = binding.tvNote.text?.toString() ?: ""
        val paymentOnline: Boolean = binding.tvPaymentMode.text == "Payment Online"

        if (paymentOnline) {
            confirmOrderAndStartWaitingActivity(addressId, voucherId, paymentOnline, note)
        } else {
            confirmOrderAndStartSuccessActivity(addressId, voucherId, false, note)
        }
    }

    private fun updateOrder(addressId: Int, voucherId: Long?, paymentOnline: Boolean, note: String?) {
        orderViewModel.createOrder(
            this, addressId, voucherId, paymentOnline, note,
            onSuccess = { updateOrder ->
                handleUpdatedOrder(updateOrder)
            }
        ) { errorMessage ->
            handleOrderError(errorMessage)
        }
    }

    private fun handleUpdatedOrder(updateOrder: Order) {
        val checkoutItems = updateOrder.cartItems?.toMutableList()
        if (checkoutItems != null) {
            checkoutAdapter.updateCheckoutItems(checkoutItems)
        }
        order = updateOrder
        with(binding) {
            tvCheckoutSubTotal.text = updateOrder.totalPrice.removeDecimalIfZero() + "đ"
            tvCheckoutShippingCharge.text = updateOrder.shipPrice.removeDecimalIfZero() + "đ"
            tvDiscount.text = updateOrder.discount.removeDecimalIfZero() + "đ"
            tvCheckoutTotalAmount.text = updateOrder.amount.removeDecimalIfZero() + "đ"
        }
    }

    private fun handleOrderError(errorMessage: String) {
        Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        Log.e("CheckoutActivity", "Error: $errorMessage")
    }

    private fun confirmOrderAndStartWaitingActivity(addressId: Int, voucherId: Long, paymentOnline: Boolean, note: String?) {
        orderViewModel.confirmOrder(
            this, addressId, voucherId, paymentOnline, note,
            onSuccess = { order ->
                startWaitingActivity()
            }
        ) { errorMessage ->
            handleOrderError(errorMessage)
        }
    }

    private fun confirmOrderAndStartSuccessActivity(addressId: Int, voucherId: Long, paymentOnline: Boolean, note: String?) {
        confirmOrder(addressId, voucherId, paymentOnline, note)
        val intent = Intent(this, OrderSuccessFulActivity::class.java)
        startActivity(intent)
    }

    private fun confirmOrder(addressId: Int, voucherId: Long?, paymentOnline: Boolean, note: String?) {
        orderViewModel.confirmOrder(
            this, addressId, voucherId, paymentOnline, note,
            onSuccess = {
                Log.d("CheckoutActivity", "Order confirmation successful. Order ID")
            }
        ) { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            Log.e("CheckoutActivity", "Error: $errorMessage")
        }

    }

    private fun startWaitingActivity() {
        val intent = Intent(this, WaitingForPaymentActivity::class.java)
        startActivity(intent)
    }

    private fun startSuccessActivity() {
        val intent = Intent(this, OrderSuccessFulActivity::class.java)
        startActivity(intent)
    }

    private fun Double.removeDecimalIfZero(): String {
        val stringValue = this.toString()
        return if (stringValue.endsWith(".0")) {
            stringValue.substring(0, stringValue.length - 2)
        } else {
            stringValue
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CHOOSE_VOUCHER_REQUEST_CODE -> handleVoucherActivityResult(resultCode, data)
            PAYMENT_REQUEST_CODE -> handlePaymentActivityResult(resultCode, data)
        }
    }

    private fun handleVoucherActivityResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val selectedVoucherId = data?.getLongExtra("selectedVoucherId", -1)
            val selectedVoucherCode = data?.getStringExtra("selectedVoucherCode")

            if (selectedVoucherId != null && selectedVoucherId != -1L) {
                voucherId = selectedVoucherId
                binding.etVoucher.text = selectedVoucherCode
                updateOrder(addressId, selectedVoucherId, false, null)
            } else {
                Log.e("CheckoutActivity", "No voucher selected or an error occurred")
            }
        } else {
            Log.e("CheckoutActivity", "Voucher selection canceled")
        }
    }

    private fun handlePaymentActivityResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val selectedPaymentMethod = data?.getStringExtra("selectedPaymentMethod")
            if (selectedPaymentMethod == "cash") {
                binding.tvPaymentMode.text = "Cash On Delivery"
            }
            if (selectedPaymentMethod == "online") {
                binding.tvPaymentMode.text = "Payment Online"
            }
        } else {
            // Handle the case when payment selection is canceled
        }
    }
}
