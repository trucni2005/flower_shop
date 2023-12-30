package com.example.sweetflowershop.ui.view.product

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.databinding.ActivitySearchBinding
import com.example.sweetflowershop.ui.adapter.SearchProductItemAdapter
import com.example.sweetflowershop.ui.viewmodel.HomeViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var searchProductAdapter: SearchProductItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupSearchView()
        setupRecyclerView()

        viewModel.filteredProductsLiveData.observe(this, Observer { filteredProducts ->
            Log.d("SearchActivity", "LiveData Updated: $filteredProducts")
            searchProductAdapter.setData(filteredProducts.toMutableList())
        })

    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQueryAndFetchProducts(newText)
                if (newText != null) {
                    Log.d("SearchActivity", newText)
                }
                return true
            }
        })
    }

    private fun setupRecyclerView() {
        searchProductAdapter = SearchProductItemAdapter(mutableListOf())
        binding.rvVouchers.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchProductAdapter
        }
    }
}
