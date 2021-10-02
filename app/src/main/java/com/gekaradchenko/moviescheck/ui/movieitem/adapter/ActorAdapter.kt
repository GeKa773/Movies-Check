package com.gekaradchenko.moviescheck.ui.movieitem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gekaradchenko.moviescheck.data.Actor
import com.gekaradchenko.moviescheck.data.MovieData
import com.gekaradchenko.moviescheck.databinding.ItemActorBinding
import com.gekaradchenko.moviescheck.ui.movies.adapter.MoviesAdapter

class ActorAdapter : ListAdapter<Actor, ActorAdapter.ActorViewHolder>(DiffCallback) {

    inner class ActorViewHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Actor) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem.name == newItem.name
        }
    }
}