package com.example.newstore.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newstore.R
import com.example.newstore.adapter.product.ProductAdapter
import com.example.newstore.adapter.product.ProductState
import com.example.newstore.databinding.ActivityProductListBinding
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity(), RecyclerAdapterListener {


    lateinit var binding: ActivityProductListBinding
    lateinit var adapter: ProductAdapter
    private val productListActivityVM : ProductListActivityVM by viewModels()
    var error:String =""









    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding(R.layout.activity_product_list)


        val value = intent.extras?.getString("Key")

        productListActivityVM.homeLiveDataP.observe(this){
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                }
                is NetworkResult.Success -> {
                    createRecyclerListProduct(it.data)
                    binding.progressBar.visibility = View.GONE


                }
                is NetworkResult.Failure -> {
                    val error = it.errorMessage
                    binding.progressBar.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE

                }
                else -> {}

            }//close when

        }//close observe

        productListActivityVM.listCateProducts("api/v1/categories/$value/products")

        binding.icBack.setOnClickListener {
            finish()
        }


    }




    private fun initBinding(layout: Int) {
        binding = DataBindingUtil.setContentView(this, layout)
        binding.presenter = this

    }

    fun createRecyclerListProduct(productList: MutableList<ProductM>) {

        adapter = ProductAdapter(this,productList,ProductState.Item,this)
        binding.rvProductList.layoutManager = GridLayoutManager(this, 2)
        binding.rvProductList.adapter = adapter


    }

    override fun updateFav(productModel: ProductM) {
        TODO("Not yet implemented")
    }

    override fun updateSlc(productModel: ProductM, number: Int) {
        TODO("Not yet implemented")
    }

    override fun type(name: String) {
        TODO("Not yet implemented")
    }


}

