package com.example.newstore.api

import com.example.newstore.model.UserM
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*


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

    @FormUrlEncoded
    @POST("api/v1/users/")
    suspend fun updateUser(
        @Field ("name") name: String,
        @Field ("avatar") image: String,
        @Field ("email") email: String,
        @Field ("password") password: String,
        @Field ("role") role: String?
    ): JsonObject



}