import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetflowershop.databinding.FragmentCategoryBinding
import com.example.sweetflowershop.ui.adapter.CategoryAdapter
import com.example.sweetflowershop.data.repository.CategoryRepository
import com.example.sweetflowershop.ui.viewmodel.CategoryViewModel

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryApiServices: CategoryRepository
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryApiServices = CategoryRepository()
        categoryAdapter = CategoryAdapter(ArrayList())

        binding.rvCategories.adapter = categoryAdapter
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())

        categoryViewModel.fetchCategories()

        categoryViewModel.getCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories ->
            categoryAdapter.updateCategories(categories)
        })
    }
}

