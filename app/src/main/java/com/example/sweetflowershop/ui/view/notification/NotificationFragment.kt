package com.example.sweetflowershop.ui.view.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.databinding.FragmentNotificationBinding
import com.example.sweetflowershop.ui.adapter.NotificationAdapter
import com.example.sweetflowershop.ui.viewmodel.NotificationViewModel
import androidx.lifecycle.Observer  // Import Observer

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var viewModel: NotificationViewModel
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        adapter = NotificationAdapter(viewModel) // Pass the viewModel to the adapter

        binding.rvMyOrderItemsList.adapter = adapter
        binding.rvMyOrderItemsList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.notificationList.observe(viewLifecycleOwner, Observer { notifications ->
            adapter.submitList(notifications)
        })

        context?.let { viewModel.fetchNotificationList(it) }  // Assuming this is a function in your ViewModel to fetch notifications
    }
}
