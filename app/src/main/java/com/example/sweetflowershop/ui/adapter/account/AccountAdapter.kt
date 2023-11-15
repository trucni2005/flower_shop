package com.example.sweetflowershop.ui.adapter.account

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.customer_account.Account
import com.example.sweetflowershop.ui.viewmodel.account.AccountViewModel

import com.example.sweetflowershop.databinding.FragmentAccountBinding
import com.example.sweetflowershop.ui.view.account.CustomerInformationActivity

class AccountAdapter(private val accountViewModel: AccountViewModel) :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    private var account: Account? = null

    fun setAccount(account: Account) {
        this.account = account
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentAccountBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        account?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class ViewHolder(private val binding: FragmentAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(account: Account) {
            binding.apply {
                profileNameProfileFrag.text = account.fullName
                profileEmailProfileFrag.text = account.email

                settingCdProfileFrag.setOnClickListener {
                    val context = itemView.context
                    val intent = Intent(context, CustomerInformationActivity::class.java)
                    context.startActivity(intent)
                }
            }
    }
}
}