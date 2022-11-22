package com.example.newstore.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstore.R
import com.example.newstore.adapter.product.ProductAdapter
import com.example.newstore.adapter.product.ProductState
import com.example.newstore.databinding.ActivitySearchBinding
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.model.ProductM
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), RecyclerAdapterListener {


    lateinit var binding: ActivitySearchBinding
    lateinit var adapter: ProductAdapter
    var error:String =""
    var myList : MutableList<ProductM> = ArrayList()
    var newList : MutableList<ProductM> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_search)

        myList = intent.extras?.get("myList") as MutableList<ProductM>


        binding.expandableLayoutHrz.toggle()
        binding.icExpand.isVisible = true




        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                searchProduct(s)
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable) {

            }
        })

        binding.icExpand.setOnClickListener {
            binding.icSearch.isVisible = true
            binding.expandableLayoutHrz.toggle()
            binding.etSearch.setText("")
            finish()

        }//بستن سرچ

        binding.icSearch.setOnClickListener {
            binding.expandableLayoutHrz.collapse()
            binding.expandableLayoutHrz.toggle()

        }//کلیک دوباره ایکون سرچ

//        productListActivityVM.homeLiveDataP.observe(this){
//            when (it) {
//                is NetworkResult.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
//
//                }
//                is NetworkResult.Success -> {
//                    listProduct = it.data
//                    binding.progressBar.visibility = View.GONE
//
//
//                }
//                is NetworkResult.Failure -> {
//                    val error = it.errorMessage
//                    binding.progressBar.visibility = View.GONE
//                    binding.error.visibility = View.VISIBLE
//
//                }
//                else -> {}
//
//            }//close when
//
//        }//close observe



//        binding.icBack.setOnClickListener {
//            finish()
        }



    fun initBinding(layout: Int) {
        binding = DataBindingUtil.setContentView(this, layout)
        binding.presenter = this

    }


    override fun update(productModel: ProductM) {
    }

    override fun type(name: String) {
        TODO("Not yet implemented")
    }


    fun createRecyclerSearchProduct(newList: MutableList<ProductM>) {
        adapter = ProductAdapter(this, newList, ProductState.Search,this)
        binding.rvProductList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.rvProductList.adapter = adapter


    }





    fun searchProduct(str: CharSequence){
        newList.clear()
        myList.forEach {
            if (it.title.contains(str.toString(), ignoreCase = true)){
                newList.add(it)
            }

        }

        createRecyclerSearchProduct(newList)


    }

}









