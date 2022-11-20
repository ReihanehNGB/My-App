package com.example.newstore.adapter.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.newstore.R
import com.example.newstore.adapter.BaseRecyclerAdapter
import com.example.newstore.databinding.ProductItemBinding
import com.example.newstore.databinding.ProductItemCartBinding
import com.example.newstore.databinding.ProductItemFavoriteBinding
import com.example.newstore.databinding.ProductItemSearchBinding
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.model.ProductM
import com.example.newstore.ui.details.ProductDetailsActivity

class ProductAdapter(
    context: Context?,
    list: List<ProductM?>?,
    private val state: ProductState,
    private val recyclerAdapterListener: RecyclerAdapterListener
) : BaseRecyclerAdapter<ProductM?>(context, list) {

    override fun getRootLayoutId(): Int {
        return when (state) {
            is ProductState.Item -> R.layout.product_item
            is ProductState.Search -> R.layout.product_item_search
            is ProductState.Favorite -> R.layout.product_item_favorite
            else -> {
                R.layout.product_item_cart
            }
        }
    }

    override fun onBind(viewHolder: BaseViewHolder, position: Int) {
        val productModel = viewHolder.getData(position) as ProductM

        when (state) {
            is ProductState.Item -> {
                val itemBinding = viewHolder.binding as ProductItemBinding

                itemBinding.cvProduct.setOnClickListener {
                    context.startActivity(
                        Intent(context, ProductDetailsActivity::class.java)
                            .putExtra("singleP", productModel.id.toString())
                    )
                }
                itemBinding.icFavorite.setOnClickListener {

                    recyclerAdapterListener?.update(productModel)
                }

                itemBinding.product = productModel
            }

            is ProductState.Search -> {
                val searchBinding = viewHolder.binding as ProductItemSearchBinding
                searchBinding.cvProduct.setOnClickListener {
                    val intent = Intent(context, ProductDetailsActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("singleP", "${productModel.id}")
                    intent.putExtras(bundle)
                    ContextCompat.startActivity(context, intent, null)
                }


                searchBinding.product = productModel

            }

            is ProductState.Favorite -> {
                val favoriteBinding = viewHolder.binding as ProductItemFavoriteBinding
                favoriteBinding.product = productModel

                Glide.with(context).load(productModel.images[0])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(favoriteBinding.ivProduct)

                favoriteBinding.delete.setOnClickListener {
//                    Toast.makeText(context, "${productModel.title} removed", Toast.LENGTH_SHORT)
//                        .show()
                    recyclerAdapterListener.update(productModel)

                }
            }

            is ProductState.Cart -> {
                val cartBinding = viewHolder.binding as ProductItemCartBinding
                cartBinding.tvNumber.text = itemCount.toString()
                cartBinding.ivPlus.setOnClickListener {


                }
                cartBinding.ivMinus.setOnClickListener {
                    if (productModel.numbers > 1) {
                        productModel.numbers--
                    }
                }
                Glide.with(context).load(productModel.images[0])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(cartBinding.ivProduct)

                cartBinding.product = productModel
            }

            else -> {}
        }


    }


}