package com.example.sweetflowershop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ArrayAdapter<T>(context: Context, private val items: List<T>) :
    ArrayAdapter<T>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position) ?: return convertView ?: View(context)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)

        val itemNameTextView: TextView = view.findViewById(android.R.id.text1)

        // Assumption: T type has a "name" property, adjust accordingly if needed
        val itemName = getItemName(item)
        itemNameTextView.text = itemName

        return view
    }

    private fun getItemName(item: T): String {
        // Assumption: T type has a "name" property, adjust accordingly if needed
        return item.toString()
    }
}
