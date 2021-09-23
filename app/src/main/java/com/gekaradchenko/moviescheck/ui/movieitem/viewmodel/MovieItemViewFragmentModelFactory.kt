package com.gekaradchenko.moviescheck.ui.movieitem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MovieItemViewFragmentModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieItemFragmentViewModel::class.java)) {
            return MovieItemFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}