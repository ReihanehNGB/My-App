package com.example.newstore.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstore.R
import com.example.newstore.adapter.BaseRecyclerAdapter.*
import com.example.newstore.adapter.product.ProductAdapter
import com.example.newstore.adapter.product.ProductState
import com.example.newstore.databinding.*
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.model.CategoryM
import com.example.newstore.model.ProductM
import com.example.newstore.ui.ProductListActivity
import com.example.newstore.ui.home.HomeFragment
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent


class CategoryAdapter(
    context: Context?,
    private val state: ProductState,
    list: List<CategoryM>,
    private val recyclerAdapterListener: RecyclerAdapterListener?

) :
    BaseRecyclerAdapter<CategoryM?>(context, list) {
    override fun getRootLayoutId(): Int {
        return when(state){
            is ProductState.CategoryName -> R.layout.category_item_name
            else -> {R.layout.category_item}
        }
    }




override fun onBind(viewHolder: BaseViewHolder, position: Int) {
    val cateModel = viewHolder.getData(position) as CategoryM

    when(state){
        is ProductState.Category ->{val cateBinding = viewHolder.binding as CategoryItemBinding
            cateBinding.category = cateModel

            cateBinding.ivCategory.setOnClickListener {
                val intent = Intent(context, ProductListActivity::class.java)
                val bundle = Bundle()
                bundle.putString("Key", "${cateModel.id}")
                intent.putExtras(bundle)
                startActivity(context,intent,null)


            }}

        is ProductState.CategoryName ->{

            val cateNameBinding = viewHolder.binding as CategoryItemNameBinding

            cateNameBinding.category = cateModel
            cateNameBinding.tvCategory.setOnClickListener {
                    recyclerAdapterListener?.type(cateModel.name)

//                val intent = Intent(context, ProductListActivity::class.java)
//                val bundle = Bundle()
//                bundle.putString("Key", "${cateModel.id}")
//                intent.putExtras(bundle)
//                startActivity(context,intent,null)
            }}
        else -> {}
    }





}


//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//        val categoryModel: CategoryM = list[position]
//
//        when (type) {
//
//            "CATEGORY-PAGE" -> {
//
//                Glide.with(context).load(categoryModel.image).centerCrop().into(binding.ivCategory)
//
//                binding.category = categoryModel
//
//                binding.ivCategory.setOnClickListener {
//
//
//                    val intent = Intent(context, ProductListActivity::class.java)
//                    val bundle = Bundle()
//                    bundle.putString("Key", "${categoryModel.id}")
//                    intent.putExtras(bundle)
//                    startActivity(context,intent,null)
//
//
//                }
//            }//close category-page
//
//            else -> {
//
////                val intent = Intent(context, ProductListActivity::class.java)
////                val bundle = Bundle()
////                bundle.putString("Key", "${categoryModel.id}")
////                intent.putExtras(bundle)
////                startActivity(context,intent,null)
//
//
//            }//close else
//
//            }
//
//
//
//
//
//
//
//
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }





}