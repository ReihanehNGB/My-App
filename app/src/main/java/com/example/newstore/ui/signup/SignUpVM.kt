package com.example.newstore.ui.signup

import androidx.lifecycle.*
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.UserM
import com.example.newstore.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpVM
@Inject constructor(
    private val userRepository: UserRepository


) : ViewModel() {

    val profLiveDataUser: MutableLiveData<NetworkResult<UserM>> = MutableLiveData()

    fun createAccount(name:String,image:String,email: String, password: String,  role:String){
        viewModelScope.launch {
            userRepository.updateUser(name,image,email,password, role).collect{
                profLiveDataUser.postValue(it)
            }
        }
    }
}










