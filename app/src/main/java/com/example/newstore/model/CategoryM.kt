package com.example.newstore.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.newstore.BR
import java.io.Serializable

data class CategoryM(

    var id: Int,
    var name: String = "",
    var image: String = ""
) : BaseObservable(), Serializable {


    @Bindable
    var selected: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.selected)
        }
}
