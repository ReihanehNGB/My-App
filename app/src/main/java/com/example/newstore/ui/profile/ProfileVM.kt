package com.example.newstore.ui.details

import androidx.lifecycle.*
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.example.newstore.model.UserM
import com.example.newstore.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileVM
@Inject constructor(
    private val userRepository: UserRepository


) : ViewModel() {

    val profLiveDataU: MutableLiveData<NetworkResult<MutableList<UserM>>> = MutableLiveData()


//    fun singleUser(url:String) {
//
//        homeLiveDataU.postValue(NetworkResult.Loading(true))
//
//
//        viewModelScope.launch {
//            userRepository.getSingleUser(url).collect{
//
//                homeLiveDataU.postValue(it)
//            }
//
//
//        }
//
//
//
//
//    }


    fun allUser() {

        profLiveDataU.postValue(NetworkResult.Loading(true))


        viewModelScope.launch {
            userRepository.getAllUser().collect{

                profLiveDataU.postValue(it)

            }


        }




    }


}










