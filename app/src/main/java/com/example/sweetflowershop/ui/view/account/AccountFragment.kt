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

        setupAccountObserver()
        setupClickListeners()

        return binding.root
    }

    private fun setupAccountObserver() {
        accountViewModel.accountLiveData.observe(viewLifecycleOwner) { account ->
            Log.d("AccountFragment", "Observer called with account: $account")
            account?.let {
                binding.account = it
                val imageUrl = _Constant.baseUrl_ + "images/customer/${account.avatar}"
                Picasso.get().load(imageUrl).into(binding.profileImageProfileFrag)
            }
        }

        fetchAccount()
    }

    private fun setupClickListeners() {
        binding.settingCdProfileFrag.setOnClickListener { openCustomerInformationActivity() }
        binding.constraintLogout.setOnClickListener { logoutAndLaunchLoginActivity() }
        binding.cvMyOrders.setOnClickListener { openViewOrderHistoryActivity() }
        binding.promoCodes.setOnClickListener { fetchAndOpenChooseVoucherActivity() }
        binding.shippingAddressCardProfilePage.setOnClickListener { openChooseAddressActivity() }
    }

    private fun openCustomerInformationActivity() {
        accountViewModel.getAccount(requireContext())?.let { account ->
            Log.d("DEBUG TEST SETTING", account.toString())
            val intent = Intent(requireContext(), CustomerInformationActivity::class.java)
            intent.putExtra("account", account)
            startActivity(intent)
        } ?: run {
            Log.e("Account", "Account is null")
        }
    }

    private fun logoutAndLaunchLoginActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.putExtra("shouldLaunchLoginActivity", true)
        startActivity(intent)
    }

    private fun openViewOrderHistoryActivity() {
        val intent = Intent(requireContext(), ViewOrderHistoryActivity::class.java)
        startActivity(intent)
    }

    private fun fetchAndOpenChooseVoucherActivity() {
        voucherViewModel.fetchVouchers(requireContext())
        voucherViewModel.vouchersLiveData.observe(viewLifecycleOwner) { vouchers ->
            vouchers?.let {
                openChooseVoucherActivity(it)
            }
        }
    }

    private fun openChooseAddressActivity() {
        val intent = Intent(requireContext(), ChooseAddressActivity::class.java)
        startActivity(intent)
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
