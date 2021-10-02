package com.gekaradchenko.moviescheck.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gekaradchenko.moviescheck.data.MovieData
import com.gekaradchenko.moviescheck.databinding.ItemMovieBinding


class MoviesAdapter(private val click: (item: MovieData) -> Unit) :
    ListAdapter<MovieData, MoviesAdapter.MovieViewHolder>(DiffCallback) {
    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                click.invoke(getItem(adapterPosition))
            }
        }

        fun bind(item: MovieData) {
            binding.data = item
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    private companion object DiffCallback : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem.name == newItem.name
        }
    }


}