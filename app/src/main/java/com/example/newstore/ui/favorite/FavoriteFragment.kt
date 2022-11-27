package com.example.newstore.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstore.R
import com.example.newstore.adapter.product.ProductAdapter
import com.example.newstore.adapter.product.ProductState
import com.example.newstore.databinding.FavoriteFragmentBinding
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.model.ProductM
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import java.util.*


@AndroidEntryPoint
class FavoriteFragment : Fragment(), RecyclerAdapterListener {

    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var adapterfav: ProductAdapter
    private var selectedProduct: MutableList<ProductM> = ArrayList()
    var productM: ProductM? = null


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





    override fun updateFav(productModel: ProductM) {

        if (selectedProduct.contains(productModel)) {
            selectedProduct.remove(productModel)
            Toast.makeText(
                requireContext(),
                "The ${productModel.title} removed to favorites list !",
                Toast.LENGTH_SHORT
            ).show()
            createRecyclerView(selectedProduct)
        } else {
            selectedProduct.add(productModel)
            Toast.makeText(context, "Added to favorites list", Toast.LENGTH_SHORT).show()
            createRecyclerView(selectedProduct)
        }

    }

    override fun updateSlc(productModel: ProductM) {
        TODO("Not yet implemented")
    }

    override fun type(name: String) {
        TODO("Not yet implemented")
    }

    private fun createRecyclerView(list: MutableList<ProductM>?) {
        adapterfav = ProductAdapter(requireContext(), list, ProductState.Favorite,this)
        binding.rvProductFav.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductFav.adapter = adapterfav
    }

    fun clickAddCart(view:View){
        EventBus.getDefault().post(productM)
    }






}