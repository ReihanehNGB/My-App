package com.example.newstore.api

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface ApiService {

    @GET("api/v1/products")
    suspend fun getAllProduct(): JsonArray

    @GET("api/v1/categories")
    suspend fun getCat(): JsonArray

    @GET("api/v1/users")
    suspend fun getAllUser(): JsonArray


//    @GET("api/v1/categories/1/products")
//    suspend fun getCategorizedProducts(): JsonArray


    @GET
    suspend fun reposList(@Url url: String?): JsonArray

    @GET
    suspend fun singleP(@Url url: String?): JsonObject

    @GET
    suspend fun singleU(@Url url: String?): JsonObject











}