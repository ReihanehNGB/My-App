package com.example.newstore.adapter.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.SwipeListener
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
    private val recyclerAdapterListener: RecyclerAdapterListener?
) : BaseRecyclerAdapter<ProductM?>(context, list) {

    override fun getRootLayoutId(): Int {
        return when (state) {
            is ProductState.Item -> R.layout.product_item
            is ProductState.Cart ->  R.layout.product_item_cart
            is ProductState.Favorite -> R.layout.product_item_favorite
            else -> {
                R.layout.product_item_search

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
                            .putExtra("singleP", productModel.id.toString()))
                }
                itemBinding.icFavorite.setOnClickListener {
                    if (productModel.stateFavt){
                        productModel.stateFavt = false
                        recyclerAdapterListener?.updateFav(productModel)
                    }else{
                        productModel.stateFavt = true
                        recyclerAdapterListener?.updateFav(productModel)
                    }
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

                favoriteBinding.delete.setOnClickListener {
                    productModel.stateFavt = false
                    recyclerAdapterListener?.updateFav(productModel)
                }
                favoriteBinding.swipe.setShowMode(SwipeLayout.ShowMode.LayDown)
                favoriteBinding.swipe.addDrag(SwipeLayout.DragEdge.Left, favoriteBinding.bottomWrapper)
                favoriteBinding.swipe.addSwipeListener(object : SwipeListener {
                    override fun onClose(layout: SwipeLayout) {
                        //when the SurfaceView totally cover the BottomView.
                    }
                    override fun onUpdate(layout: SwipeLayout, leftOffset: Int, topOffset: Int) {
                        //you are swiping.
                    }
                    override fun onStartOpen(layout: SwipeLayout) {}
                    override fun onOpen(layout: SwipeLayout) {
                        //when the BottomView totally show.
                    }
                    override fun onStartClose(layout: SwipeLayout) {}
                    override fun onHandRelease(layout: SwipeLayout, xvel: Float, yvel: Float) {
                        //when user's hand released.
                    }
                })
            }

            is ProductState.Cart -> {
                val cartBinding = viewHolder.binding as ProductItemCartBinding
                cartBinding.tvNumber.text = itemCount.toString()
                cartBinding.ivMinus.setOnClickListener {
                    if (productModel.numbers > 1) {
                        productModel.numbers--
                    }else{
                        recyclerAdapterListener?.updateSlc(productModel,0)

                    }
                }
                cartBinding.ivPlus.setOnClickListener {
                        productModel.numbers++
                }
                cartBinding.product = productModel
            }

            else -> {}
        }


    }


}