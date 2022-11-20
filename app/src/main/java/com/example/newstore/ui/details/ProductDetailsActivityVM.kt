package com.example.newstore.ui.details

import androidx.lifecycle.*
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.example.newstore.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsActivityVM
@Inject constructor(
    private val productRepository: ProductRepository


) : ViewModel() {

    val homeLiveDataSP: MutableLiveData<NetworkResult<ProductM>> = MutableLiveData()

    fun singleProduct(url:String) {

        homeLiveDataSP.postValue(NetworkResult.Loading(true))


        viewModelScope.launch {
            productRepository.getSingleProduct(url).collect{

                homeLiveDataSP.postValue(it)
            }


        }




    }


}










