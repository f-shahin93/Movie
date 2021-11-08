package com.shahin.movieapp.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shahin.data.model.MovieShort
import com.shahin.movieapp.databinding.ItemMovieBinding

class MovieListAdapter(val lifecycleOwner: LifecycleOwner, val listener: MovieItemClickListener) :
    ListAdapter<MovieShort, MovieListAdapter.MovieViewHolder>(MovieDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply { lifecycleOwner = this@MovieListAdapter.lifecycleOwner }, listener
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class MovieViewHolder(val binding: ItemMovieBinding, listener: MovieItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        private var itemVH: MovieShort? = null

        init {
            binding.root.setOnClickListener {
                itemVH?.let { item -> listener.onItemClicked(item.id) }
            }
        }

        fun bind(item: MovieShort) {
            itemVH = item
            binding.run {
                title = item.title
                icPath = item.backdropPath
                overview = item.overview
                rate = item.voteAverage.toString()
            }
        }
    }

    interface MovieItemClickListener {
        fun onItemClicked(movieId: Long)
    }

}

class MovieDiffUtilCallback : DiffUtil.ItemCallback<MovieShort>() {
    override fun areItemsTheSame(oldItem: MovieShort, newItem: MovieShort): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieShort, newItem: MovieShort): Boolean =
        oldItem == newItem

}