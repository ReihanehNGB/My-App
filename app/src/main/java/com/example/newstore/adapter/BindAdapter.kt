package com.example.newstore.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


object BindAdapter {


        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            Glide.with(view.context).load(url)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view)
    }

//        @SuppressLint("ResourceType")
//        @JvmStatic
//        @BindingAdapter("gifPath")
//        private fun loadGif(view: ImageView, path:Drawable){
//            Glide.with(view.context)
//                .asGif()
//                .load(path)
//                .into(view)
//        }





}