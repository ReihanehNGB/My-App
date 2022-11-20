package com.example.newstore.repository

import android.util.Log
import com.example.newstore.api.ApiService
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flow

import java.lang.reflect.Type
import java.util.ArrayList
import javax.inject.Inject


class ProductRepository @Inject constructor(
    private val apiService: ApiService

) {


    suspend fun getListProducts() = flow<NetworkResult<MutableList<ProductM>>> {
        try {
            val result = apiService.getAllProduct()

            val listType: Type? = object : TypeToken<ArrayList<ProductM?>?>() {}.type
            val data: MutableList<ProductM> = Gson().fromJson(result, listType)
            emit(NetworkResult.Success(data))


        } catch (e: Exception) {
            emit(NetworkResult.Failure("Error:${e.message}"))
        }//close catch

    }//close fun getListProduct


    suspend fun getCateProducts(url: String) = flow<NetworkResult<MutableList<ProductM>>> {
        try {
            val result = apiService.reposList(url)

            val listType: Type? = object : TypeToken<ArrayList<ProductM?>?>() {}.type
            val data: MutableList<ProductM> = Gson().fromJson(result, listType)
            emit(NetworkResult.Success(data))


        } catch (e: Exception) {
            emit(NetworkResult.Failure("Error:${e.message}"))

        }//close catch

    }//close fun getCateProduct


    suspend fun getSingleProduct(url: String) = flow<NetworkResult<ProductM>> {


        try {
            val result = apiService.singleP(url)

            val data: ProductM = Gson().fromJson(result, ProductM::class.java)
            emit(NetworkResult.Success(data))


        } catch (e: Exception) {
            emit(NetworkResult.Failure("Error:${e.message}"))
        }//close catch

    }//close fun getSingleProduct


}