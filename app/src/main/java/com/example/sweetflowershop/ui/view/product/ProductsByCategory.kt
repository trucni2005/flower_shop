package com.example.sweetflowershop.ui.view.product

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sweetflowershop.databinding.ActivityProductByCategoryBinding
import com.example.sweetflowershop.data.model.product.Product
import com.example.sweetflowershop.ui.adapter.ProductsAdapter
import com.example.sweetflowershop.data.repository.CategoryRepository
import com.example.sweetflowershop.data.repository.ProductRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductsByCategory : AppCompatActivity() {
    private lateinit var binding: ActivityProductByCategoryBinding
    private lateinit var categoryApiServices: CategoryRepository
    private lateinit var productApiServices: ProductRepository
    private val products = ArrayList<Product>()
    private lateinit var productAdapter: ProductsAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityProductByCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val categoryId = intent.getIntExtra("categoryId", 0)
        fetchProductsByCategory(categoryId)
    }

    private fun setupRecyclerView() {
        productAdapter = ProductsAdapter(products)
        binding.rvProducts.adapter = productAdapter
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
    }

    private fun fetchProductsByCategory(categoryId: Int) {
        categoryApiServices = CategoryRepository()
        productApiServices = ProductRepository()

        val disposable = productApiServices.getProductsbyCategory(categoryId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                override fun onSuccess(receivedProducts: List<Product>) {
                    products.addAll(receivedProducts)
                    productAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    e.message?.let { Log.d("DEBUG", "Fail $it") }
                }
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
