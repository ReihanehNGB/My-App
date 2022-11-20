package com.example.newstore.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.R
import com.example.newstore.adapter.CategoryAdapter
import com.example.newstore.adapter.product.ProductState
import com.example.newstore.databinding.CategoryFragmentBinding
import com.example.newstore.model.CategoryM
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.example.newstore.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {


    lateinit var name: String
    private lateinit var binding: CategoryFragmentBinding
    lateinit var categoryadapter: CategoryAdapter




    companion object {
        fun newInstance() = CategoryFragment()
    }

    private val cateVM: CategoryVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.category_fragment, container, false)
        binding.presenter = this

        cateVM.listCategories()

        cateVM.homeLiveDataC.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    createRecyclerCategory(it.data)
                    binding.progressBar.visibility = View.GONE
                }
                is NetworkResult.Failure -> {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                }
            }
        }

        return binding.root

    }// close onCreateView

    fun createRecyclerCategory(cateList: MutableList<CategoryM>) {
        categoryadapter = CategoryAdapter(requireContext(),ProductState.Category, cateList,null)
        binding.rvCategory.layoutManager =
            GridLayoutManager(requireContext(), 1)
        binding.rvCategory.adapter = categoryadapter
    }

}// close class