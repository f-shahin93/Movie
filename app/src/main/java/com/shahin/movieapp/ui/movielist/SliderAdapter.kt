package com.shahin.movieapp.ui.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shahin.data.model.MovieShort
import com.shahin.movieapp.databinding.ItemImgSliderBinding

class SliderAdapter(val lifecycleOwner: LifecycleOwner) :
    ListAdapter<MovieShort, SliderAdapter.SliderViewHolder>(SliderDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder(
            ItemImgSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply { lifecycleOwner = this@SliderAdapter.lifecycleOwner }, parent.context)

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class SliderViewHolder(val binding: ItemImgSliderBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieShort) {
            binding.urlPath = item.backdropPath
        }
    }

}

class SliderDiffUtilCallback : DiffUtil.ItemCallback<MovieShort>() {
    override fun areItemsTheSame(oldItem: MovieShort, newItem: MovieShort): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieShort, newItem: MovieShort): Boolean =
        oldItem == newItem

}