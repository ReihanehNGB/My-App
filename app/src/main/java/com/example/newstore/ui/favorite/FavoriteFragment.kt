package com.example.newstore.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstore.R
import com.example.newstore.SwipeToDeleteCallback
import com.example.newstore.adapter.product.ProductAdapter
import com.example.newstore.adapter.product.ProductState
import com.example.newstore.databinding.FavoriteFragmentBinding
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.model.ProductM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class FavoriteFragment : Fragment(), RecyclerAdapterListener {

    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var adapterfav: ProductAdapter
    private var selectedProduct: MutableList<ProductM> = ArrayList()

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false)
        binding.presenter = this

        adapterfav = ProductAdapter(requireContext(),null,ProductState.Favorite,this)

        return binding.root
    }





    override fun update(productModel: ProductM) {

        if (selectedProduct.contains(productModel)) {
            selectedProduct.remove(productModel)
//            Toast.makeText(
//                requireContext(),
//                "The ${productModel.title} removed to favorites list !",
//                Toast.LENGTH_SHORT
//            ).show()
            createRecyclerView(selectedProduct)
        } else {
//            selectedProduct.add(productModel)
//            Toast.makeText(context, "Added to favorites list", Toast.LENGTH_SHORT).show()
            selectedProduct.add(productModel)
            createRecyclerView(selectedProduct)
        }

    }

    override fun type(name: String) {
        TODO("Not yet implemented")
    }

    private fun createRecyclerView(list: MutableList<ProductM>?) {
        adapterfav = ProductAdapter(requireContext(), list, ProductState.Favorite,this)
        binding.rvProductFav.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductFav.adapter = adapterfav
    }



}