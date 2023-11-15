package com.example.sweetflowershop.ui.view.account

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.customer_account.Account
import com.example.sweetflowershop.databinding.FragmentAccountBinding
import com.example.sweetflowershop.ui.viewmodel.account.AccountViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        accountViewModel.accountLiveData.observe(viewLifecycleOwner, Observer { account ->
            account?.let {
                binding.account = it
            }
        })

        fetchAccount()

        binding.settingCdProfileFrag.setOnClickListener {
            accountViewModel.getAccount(requireContext())?.let { account ->
                val intent = Intent(requireContext(), CustomerInformationActivity::class.java)
                intent.putExtra("account", account)
                startActivity(intent)
            } ?: run {
                Log.e("Account", "Account is null")
            }
        }


        return binding.root
    }

    private fun fetchAccount() {
        accountViewModel.fetchAccount(requireContext())
    }
}
