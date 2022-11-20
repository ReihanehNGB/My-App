package com.example.newstore.repository

import com.example.newstore.api.ApiService
import com.example.newstore.model.CategoryM
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.example.newstore.model.UserM
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import java.util.ArrayList
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val apiService: ApiService

) {
    lateinit var data: MutableList<UserM>

    suspend fun getSingleUser() = flow<NetworkResult<UserM>> {


        try {
            val result = apiService.getAllUser()

            val dataS: UserM = Gson().fromJson(result, UserM::class.java)
            emit(NetworkResult.Success(dataS))


        } catch (e: Exception) {
            emit(NetworkResult.Failure("Error:${e.message}"))
        }//close catch

    }//close fun getSingleUser


    suspend fun getAllUser() = flow<NetworkResult<MutableList<UserM>>> {
        try {
            val result = apiService.getAllUser()

            val listType: Type? = object : TypeToken<ArrayList<UserM?>?>() {}.type
            val data: MutableList<UserM> = Gson().fromJson(result, listType)
            emit(NetworkResult.Success(data))


        } catch (e: Exception) {
            emit(NetworkResult.Failure("Error:${e.message}"))
        }//close catch

    }//close fun getListProduct


}