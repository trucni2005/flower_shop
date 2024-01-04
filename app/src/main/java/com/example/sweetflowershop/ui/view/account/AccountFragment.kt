package com.example.sweetflowershop.ui.view.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.voucher.Voucher
import com.example.sweetflowershop.databinding.FragmentAccountBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.ui.view.address.ChooseAddressActivity
import com.example.sweetflowershop.ui.view.main.MainActivity
import com.example.sweetflowershop.ui.view.order.PaymentActivity
import com.example.sweetflowershop.ui.view.order.ViewOrderHistoryActivity
import com.example.sweetflowershop.ui.view.voucher.ChooseVoucherActivity
import com.example.sweetflowershop.ui.viewmodel.AccountViewModel
import com.example.sweetflowershop.ui.viewmodel.VoucherViewModel
import com.squareup.picasso.Picasso

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var binding: FragmentAccountBinding
    private lateinit var voucherViewModel: VoucherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        voucherViewModel = ViewModelProvider(this).get(VoucherViewModel::class.java)

        accountViewModel.accountLiveData.observe(viewLifecycleOwner, Observer { account ->
            Log.d("AccountFragment", "Observer called with account: $account")
            account?.let {
                binding.account = it
                val imageUrl =
                    _Constant.baseUrl_ + "images/customer/${account.avatar}"
                Picasso.get().load(imageUrl).into(binding.profileImageProfileFrag)
            }
        })

        fetchAccount()

        binding.settingCdProfileFrag.setOnClickListener {
            accountViewModel.getAccount(requireContext())?.let { account ->
                Log.d("DEBUG TEST SETTING", account.toString())
                val intent = Intent(requireContext(), CustomerInformationActivity::class.java)
                intent.putExtra("account", account)
                startActivity(intent)
            } ?: run {
                Log.e("Account", "Account is null")
            }
        }

        binding.constraintLogout.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra("shouldLaunchLoginActivity", true)
            startActivity(intent)
        }

//        binding.paymentMethodProfilePage.setOnClickListener {
//            val intent = Intent(requireContext(), PaymentActivity::class.java)
//            startActivity(intent)
//        }

        binding.cvMyOrders.setOnClickListener {
            val intent = Intent(requireContext(), ViewOrderHistoryActivity::class.java)
            startActivity(intent)
        }

        voucherViewModel = ViewModelProvider(this).get(VoucherViewModel::class.java)

        binding.promoCodes.setOnClickListener {
            // Gọi phương thức fetchVouchers từ voucherViewModel
            voucherViewModel.fetchVouchers(requireContext())
        }

        binding.shippingAddressCardProfilePage.setOnClickListener{
            val intent = Intent(requireContext(), ChooseAddressActivity::class.java)
            startActivity(intent)
        }

        voucherViewModel.vouchersLiveData.observe(viewLifecycleOwner, Observer { vouchers ->
            vouchers?.let {
                // Gọi hàm openChooseVoucherActivity và truyền vào danh sách vouchers
                openChooseVoucherActivity(it)
            }
        })

        return binding.root
    }

    private fun openChooseVoucherActivity(vouchers: List<Voucher>) {
        val intent = Intent(requireContext(), ChooseVoucherActivity::class.java)
        intent.putExtra("voucherList", ArrayList(vouchers))
        startActivity(intent)
    }


    private fun fetchAccount() {
        accountViewModel.fetchAccount(requireContext())
    }
}
