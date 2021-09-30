package com.gekaradchenko.moviescheck.ui.movieitem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.moviescheck.R
import com.gekaradchenko.moviescheck.databinding.FragmentMovieItemBinding
import com.gekaradchenko.moviescheck.ui.movieitem.viewmodel.MovieItemFragmentViewModel
import com.gekaradchenko.moviescheck.ui.movieitem.viewmodel.MovieItemViewFragmentModelFactory


class MovieItemFragment : Fragment() {


    private lateinit var binding: FragmentMovieItemBinding
    private lateinit var viewModel: MovieItemFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_item, container, false)
        val factory = MovieItemViewFragmentModelFactory()
        viewModel = ViewModelProvider(this, factory)[MovieItemFragmentViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        val args = MovieItemFragmentArgs.fromBundle(requireArguments())
        println(" url = ${args.url}")



        return binding.root
    }


}