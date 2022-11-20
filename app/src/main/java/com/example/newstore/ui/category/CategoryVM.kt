package com.example.newstore.ui.category

import androidx.lifecycle.*
import com.example.newstore.model.CategoryM
import com.example.newstore.model.NetworkResult
import com.example.newstore.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryVM
@Inject constructor(
    private val categoryRepository: CategoryRepository


) : ViewModel() {

    val homeLiveDataC: MutableLiveData<NetworkResult<MutableList<CategoryM>>> = MutableLiveData()



    fun listCategories() {



        viewModelScope.launch {
            categoryRepository.getListCat().collect{

                homeLiveDataC.postValue(it)
            }


        }




    }
}










