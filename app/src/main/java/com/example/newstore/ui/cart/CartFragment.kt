package com.example.newstore.ui.cart

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
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

@AndroidEntryPoint
class CartFragment : Fragment(), RecyclerAdapterListener {//close class

    private lateinit var binding: CartFragmentBinding
    private lateinit var adapterCart: ProductAdapter
    private var addCartProduct = ArrayList<ProductM>()
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

        adapterCart = ProductAdapter(requireContext(),null,ProductState.Cart,this)

        binding.emptyBasket.visibility = View.VISIBLE






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
        binding.emptyBasket.visibility = View.GONE
        val context = requireContext()
        if (addCartProduct.contains(productModelC)) {
            Toast.makeText(
                requireContext(),
                "The product has already been added",
                Toast.LENGTH_SHORT
            ).show()
            createRecyclerView(addCartProduct)

        } else {
            addCartProduct.add(productModelC)
            Toast.makeText(context, "Added to carts list", Toast.LENGTH_SHORT).show()
        }
        createRecyclerView(addCartProduct)
        totalPrice(addCartProduct)
    }

    override fun updateFav(productModel: ProductM) {
        TODO("Not yet implemented")
    }

    override fun updateSlc(productModel: ProductM,number: Int) {
        deletSlc(productModel,number)
    }


    override fun type(name: String) {
        TODO("Not yet implemented")
    }

    fun createRecyclerView(l: MutableList<ProductM>) {
            adapterCart = ProductAdapter(requireContext(), l, ProductState.Cart, this)
            binding.rvProductCart.layoutManager = LinearLayoutManager(requireContext())
            binding.rvProductCart.adapter = adapterCart


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(productM: ProductM) {
    }



    fun deletSlc(productModel: ProductM,number: Int){
        if (number==0){
            binding.emptyBasket.visibility = View.VISIBLE
            addCartProduct.remove(productModel)
            createRecyclerView(addCartProduct)
        }
    }








}


