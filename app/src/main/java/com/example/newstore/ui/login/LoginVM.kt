package com.example.newstore.ui.login

import androidx.lifecycle.*
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.example.newstore.model.UserM
import com.example.newstore.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM
@Inject constructor(
    private val userRepository: UserRepository


) : ViewModel() {

    val loginLiveData: MutableLiveData<NetworkResult<MutableList<UserM>>> = MutableLiveData()

    fun listUser() {

        loginLiveData.postValue(NetworkResult.Loading(true))


        viewModelScope.launch {
            userRepository.getAllUser().collect{

                loginLiveData.postValue(it)
            }//close collect


        }//close lanch


    }//close listUser


}










