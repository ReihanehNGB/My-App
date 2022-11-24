package com.example.newstore.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstore.R
import com.example.newstore.adapter.product.ProductAdapter
import com.example.newstore.adapter.product.ProductState
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.databinding.CartFragmentBinding
import com.example.newstore.model.ProductM

import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CartFragment : Fragment(), RecyclerAdapterListener {//close class

    private lateinit var binding: CartFragmentBinding
    private lateinit var adapterCart: ProductAdapter
    private var selectProduct = ArrayList<ProductM>()
    var totalP: Int = 0



    companion object {
        fun newInstance() = CartFragment()
    }

    private val cartVM: CartVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.cart_fragment, container, false)
        binding.presenter = this




//        adapterCart = ProductAdapter(requireContext(), null, ProductState.Cart, this)


//        adaptercart.liveData?.observe(viewLifecycleOwner){
//            selectProduct.add(it)
//            createRecyclerView(selectProduct)
//
//        }


        return binding.root


    }//close onCreateView

    private fun totalPrice(selectProduct:MutableList<ProductM>) {
        selectProduct.forEach {

            totalP += it.price.toInt()
        }
        binding.prc = totalP.toString()
    }

    fun updateList(productModelC: ProductM) {
        val context = requireContext()
        if (selectProduct.contains(productModelC)) {
            Toast.makeText(
                requireContext(),
                "The product has already been added",
                Toast.LENGTH_SHORT
            ).show()
            createRecyclerView(selectProduct)

        } else {
            selectProduct.add(productModelC)
            Toast.makeText(context, "Added to carts list", Toast.LENGTH_SHORT).show()
        }
        totalPrice(selectProduct)
        createRecyclerView(selectProduct)


    }

    override fun update(productModel: ProductM) {
        updateList(productModel)
    }

    override fun type(name: String) {
        TODO("Not yet implemented")
    }

    fun createRecyclerView(l: MutableList<ProductM>) {
        adapterCart = ProductAdapter(requireContext(), l, ProductState.Cart, this)
        binding.rvProductCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductCart.adapter = adapterCart
    }




}


