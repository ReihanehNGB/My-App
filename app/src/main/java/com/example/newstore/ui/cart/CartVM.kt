package com.example.newstore.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.newstore.model.ProductM
import com.example.newstore.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CartVM
@Inject constructor(
    val productRepository: ProductRepository
    ) : ViewModel()
{

    val cartLiveDataP: MutableLiveData<ProductM> = MutableLiveData()

    private var count = 0

    fun getCurrentCount():Int{
        return count
    }

    fun getUpdatedCount():Int{
        return ++count
    }
}

