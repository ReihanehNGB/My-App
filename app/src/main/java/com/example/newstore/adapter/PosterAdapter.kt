package com.example.newstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.newstore.R
import com.example.newstore.databinding.PosterItemBinding
import com.example.newstore.model.PosterM

class PosterAdapter(private val context: Context, var list: MutableList<PosterM>) :
    RecyclerView.Adapter<PosterAdapter.ViewHolder>() {

    lateinit var binding: PosterItemBinding


    class ViewHolder(binding: PosterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.poster_item, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val PosterModel: PosterM = list[position]




        Glide.with(context).load(PosterModel.image).centerCrop().transform(RoundedCorners(40)).into(binding.ivPoster)

        binding.poster = PosterModel
    }

    override fun getItemCount(): Int {
        return list.size
    }


}