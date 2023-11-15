import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.databinding.FragmentCategoryBinding
import com.example.sweetflowershop.data.model.category.Category
import com.example.sweetflowershop.ui.adapter.category.CategoryAdapter
import com.example.sweetflowershop.network.apiService.category.CategoryAPIService
import com.example.sweetflowershop.ui.viewmodel.category.CategoryViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryApiServices: CategoryAPIService
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryApiServices = CategoryAPIService()
        categoryAdapter = CategoryAdapter(ArrayList())

        binding.rvCategories.adapter = categoryAdapter
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())

        categoryViewModel.fetchCategories()

        categoryViewModel.getCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories ->
            categoryAdapter.updateCategories(categories)
        })
    }
}

