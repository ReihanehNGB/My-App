package com.example.newstore.model

import java.io.Serializable

data class CategoryM(

    var id: Int ,
    var name: String = "",
    var image: String = ""
) : Serializable
