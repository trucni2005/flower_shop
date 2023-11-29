package com.example.sweetflowershop.ui.view.order

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.data.model.cart.CartItem
import com.example.sweetflowershop.data.model.order.Order
import com.example.sweetflowershop.data.model.voucher.Voucher
import com.example.sweetflowershop.databinding.ActivityCheckoutBinding
import com.example.sweetflowershop.ui.adapter.CheckoutAdapter
import com.example.sweetflowershop.ui.view.voucher.ChooseVoucherActivity
import com.example.sweetflowershop.ui.viewmodel.order.OrderViewModel

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var checkoutAdapter: CheckoutAdapter
    private lateinit var orderViewModel: OrderViewModel
    private var addressId: Int = -1
    private var order: Order? = null
    private var voucherId: Long = -1
//    private lateinit var order: Order


    companion object {
        const val CHOOSE_VOUCHER_REQUEST_CODE = 101
        const val PAYMENT_REQUEST_CODE = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val address = intent.getSerializableExtra("address") as? Address
        addressId = address?.id?.toInt() ?: -1

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        binding.orderViewModel = orderViewModel

        checkoutAdapter = CheckoutAdapter(context = this)

        if (address != null) {
            val formattedAddress =
                "${address.street}, ${address.ward}, ${address.district}, ${address.city}"
            binding.tvCheckoutFullName.text = address.nameCustomer
            binding.tvCheckoutMobileNumber.text = address.phoneNumber
            binding.tvCheckoutAddress.text = formattedAddress
        }

        if (addressId != null) {
            updateOrder(addressId, null, false, null)
        }

        binding.rvCartListItems.layoutManager = LinearLayoutManager(this)
        binding.rvCartListItems.adapter = checkoutAdapter
        binding.tvPaymentMode.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivityForResult(intent, PAYMENT_REQUEST_CODE)
        }

        binding.etVoucher.setOnClickListener {
            val voucherList: List<Voucher> = order?.vouchers ?: emptyList()
            val intent = Intent(this, ChooseVoucherActivity::class.java)
            intent.putExtra("voucherList", ArrayList(voucherList))
            startActivityForResult(intent, CHOOSE_VOUCHER_REQUEST_CODE)
        }

        binding.btnPlaceOrder.setOnClickListener {
            var note: String? = null
            if (binding.tvNote.text == null){
                note = ""
            }
            else note = binding.tvNote.text.toString()
            var paymentOnline: Boolean? = false
            if (binding.tvPaymentMode.text == "Payment Online")
            {
                paymentOnline = true
            }

            if (paymentOnline != null) {
                confirmOrder(addressId, voucherId, paymentOnline, note)
                val intent = Intent(this, WaitingForPaymentActivity::class.java)
//                intent.putExtra("ORDER_ID", orderId)
                startActivity(intent)
            }
        }
    }

    fun updateOrder(addressId: Int, voucherId: Long?, paymentOnline: Boolean, note: String?) {
            orderViewModel.createOrder(
                this, addressId, voucherId, paymentOnline, note,
                onSuccess = { updateOrder ->
                    Log.d("updateOrder", updateOrder.toString())
                    val checkoutItems = updateOrder.cartItems
                    Log.d("checkoutItems", checkoutItems.toString())
                    if (checkoutItems != null) {
                        val mutableCheckoutItems: MutableList<CartItem> =
                            updateOrder.cartItems.toMutableList()
                        checkoutAdapter.updateCheckoutItems(mutableCheckoutItems)
                        order = updateOrder
                        binding.tvCheckoutSubTotal.text = updateOrder.totalPrice.toString()
                        binding.tvCheckoutShippingCharge.text = updateOrder.shipPrice.toString()
                        binding.tvDiscount.text = updateOrder.discount.toString()
                        binding.tvCheckoutTotalAmount.text = updateOrder.amount.toString()
                        Log.d("order", order.toString())
                    }

                }
            ) { errorMessage ->
                Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
                Log.e("CheckoutActivity", "Error: $errorMessage")
            }
        }

    fun confirmOrder(addressId: Int, voucherId: Long?, paymentOnline: Boolean, note: String?) {
        orderViewModel.confirmOrder(
            this, addressId, voucherId, paymentOnline, note,
            onSuccess = { order ->
                Log.d("CheckoutActivity", "Order confirmation successful. Order ID")
            }
        ) { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            Log.e("CheckoutActivity", "Error: $errorMessage")
        }

    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == CHOOSE_VOUCHER_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedVoucherId = data?.getLongExtra("selectedVoucherId", -1)
                    val selectedVoucherCode = data?.getStringExtra("selectedVoucherCode")

                    if (selectedVoucherId != null && selectedVoucherId != -1L) {
                        Log.d("CheckoutActivity", "Selected Voucher ID: $selectedVoucherId")
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

            if (requestCode == PAYMENT_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedPaymentMethod = data?.getStringExtra("selectedPaymentMethod")
                    if (selectedPaymentMethod == "cash") {
                        binding.tvPaymentMode.text = "Cash On Delivery"

                    }
                    if (selectedPaymentMethod == "online") {
                        binding.tvPaymentMode.text = "Payment Online"
                    }
                } else {
                }
            }
        }
    }

