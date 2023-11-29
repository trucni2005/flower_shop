package com.example.sweetflowershop.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.address.Address
import com.example.sweetflowershop.ui.view.order.CheckoutActivity

class AddressAdapter(private var addresses: MutableList<Address> = mutableListOf()) :

    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_address_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val addressItem = addresses[position]
        holder.bind(addressItem)
    }

    override fun getItemCount(): Int {
        return addresses.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_address_full_name)
        val tvAddressDetails: TextView = itemView.findViewById(R.id.tv_address_details)
        val tvPhoneNumber: TextView = itemView.findViewById(R.id.tv_address_mobile_number)
            fun bind(address: Address) {
                val formattedAddress = "${address.street}, ${address.ward}, ${address.district}, ${address.city}"
                tvAddressDetails.text = formattedAddress
                tvName.text = address.nameCustomer
                tvPhoneNumber.text = address.phoneNumber

                Log.d("Address Test", "Address: $address")

                itemView.setOnClickListener {
                    val address = addresses[adapterPosition]
                    val intent = Intent(itemView.context, CheckoutActivity::class.java)
                    intent.putExtra("address", address)
                    itemView.context.startActivity(intent)
                }
            }
        }

    fun setData(newAddresses: MutableList<Address>) {
        addresses = newAddresses
        Log.d("Address", "Data Size: ${addresses.size}")
        for (address in addresses) {
            Log.d("Address", "Address: $address")
        }
        notifyDataSetChanged()
    }

}