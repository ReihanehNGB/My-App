package com.example.newstore.adapter.product

import java.io.Serializable

sealed class ProductState: Serializable {
    object Item : ProductState()
    object CategoryName : ProductState()
    object Search : ProductState()
    object Category : ProductState()
    object Favorite : ProductState()
    object Cart : ProductState()
    }
