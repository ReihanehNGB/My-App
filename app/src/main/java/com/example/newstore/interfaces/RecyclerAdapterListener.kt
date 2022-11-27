package com.example.newstore.interfaces

import com.example.newstore.model.ProductM


interface RecyclerAdapterListener {

    fun updateFav(productModel: ProductM)

    fun updateSlc(productModel: ProductM)


    fun type(name:String)


}