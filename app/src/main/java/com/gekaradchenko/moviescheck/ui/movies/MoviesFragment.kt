package com.gekaradchenko.moviescheck.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.moviescheck.R
import com.gekaradchenko.moviescheck.databinding.FragmentMoviesBinding
import com.gekaradchenko.moviescheck.network.BASE_URL
import com.gekaradchenko.moviescheck.ui.movies.viewmodel.MoviesFragmentViewModel
import com.gekaradchenko.moviescheck.ui.movies.viewmodel.MoviesFragmentViewModelFactory
import org.jsoup.Jsoup


class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        val factory = MoviesFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MoviesFragmentViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.moviesList.observe(viewLifecycleOwner,{
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        })



        return binding.root
    }


}