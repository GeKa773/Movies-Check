package com.gekaradchenko.moviescheck.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MoviesFragmentViewModelFactory(   ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesFragmentViewModel::class.java)) {
            return MoviesFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}