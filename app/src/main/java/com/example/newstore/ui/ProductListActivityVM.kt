package com.example.newstore.ui

import androidx.lifecycle.*
import com.example.newstore.model.CategoryM
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.example.newstore.repository.CategoryRepository
import com.example.newstore.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListActivityVM
@Inject constructor(
    private val productRepository: ProductRepository


) : ViewModel() {

    val homeLiveDataP: MutableLiveData<NetworkResult<MutableList<ProductM>>> = MutableLiveData()

    fun listCateProducts(url:String) {

        homeLiveDataP.postValue(NetworkResult.Loading(true))


        viewModelScope.launch {
            productRepository.getCateProducts(url).collect{

                homeLiveDataP.postValue(it)
            }


        }




    }


}










