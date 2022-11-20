package com.example.newstore.ui.home

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
class HomeVM
@Inject constructor(
    private val productRepository: ProductRepository


) : ViewModel() {

    val homeLiveDataP: MutableLiveData<NetworkResult<MutableList<ProductM>>> = MutableLiveData()

    fun listProducts() {

        homeLiveDataP.postValue(NetworkResult.Loading(true))


        viewModelScope.launch {
            productRepository.getListProducts().collect{

                homeLiveDataP.postValue(it)
            }


        }


//            productRepository.repositoryLiveData.observe()


    }


}










