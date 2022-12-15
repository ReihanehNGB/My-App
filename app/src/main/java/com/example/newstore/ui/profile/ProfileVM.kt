package com.example.newstore.ui.profile

import androidx.lifecycle.*
import com.example.newstore.model.NetworkResult
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
    val profLiveDataUser: MutableLiveData<NetworkResult<UserM>> = MutableLiveData()


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


    fun updateUser(name:String,email: String, password: String, image:String){

        viewModelScope.launch {
            userRepository.updateUser(name,email,password,image,null).collect{
                profLiveDataUser.postValue(it)
            }

        }
}






}











