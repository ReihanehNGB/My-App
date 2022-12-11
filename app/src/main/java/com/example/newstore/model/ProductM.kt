package com.example.newstore.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.newstore.BR
import java.io.Serializable

data class ProductM(
    var id: Int,
    var title: String = "",
    var price: String,
    var description: String = "",
    var category: CategoryM,
    var images: MutableList<String>
) : BaseObservable(), Serializable {

    @Bindable
    var numbers: Int = 1
        set(value) {
            field = value
            notifyPropertyChanged(BR.numbers)
        }

    @Bindable
    var state: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.state)
        }





}

