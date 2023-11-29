package com.example.sweetflowershop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.data.model.voucher.Voucher
import com.example.sweetflowershop.databinding.VoucherItemBinding

class VoucherAdapter(private val voucherList: List<Voucher>, private val onItemClick: (Voucher) -> Unit) :
    RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VoucherItemBinding.inflate(inflater, parent, false)
        return VoucherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        val voucher = voucherList[position]
        holder.bind(voucher)
    }

    override fun getItemCount(): Int {
        return voucherList.size
    }

    inner class VoucherViewHolder(private val binding: VoucherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(voucher: Voucher) {
            binding.voucher = voucher
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onItemClick.invoke(voucher)
            }
        }
    }
}
