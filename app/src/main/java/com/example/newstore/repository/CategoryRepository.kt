package com.example.newstore.repository

import com.example.newstore.api.ApiService
import com.example.newstore.model.CategoryM
import com.example.newstore.model.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import java.util.ArrayList
import javax.inject.Inject


class CategoryRepository @Inject constructor(
    private val apiService: ApiService

) {

    suspend fun getListCat() = flow<NetworkResult<MutableList<CategoryM>>> {


        try {
            val result = apiService.getCat()

            val listType: Type? = object : TypeToken<ArrayList<CategoryM?>?>() {}.type
            val data: MutableList<CategoryM> = Gson().fromJson(result, listType)
            emit(NetworkResult.Success(data))


        } catch (e: Exception) {
            emit(NetworkResult.Failure("Error${e.message}"))
        }//close catch

    }//close fun getListCat


    }