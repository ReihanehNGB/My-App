package com.example.newstore.interfaces

import com.example.newstore.model.ProductM
import okhttp3.internal.notifyAll


interface RecyclerAdapterListener {

    fun updateFav(productModel: ProductM)

    fun updateSlc(productModel: ProductM,number: Int)

    fun type(name:String)



}