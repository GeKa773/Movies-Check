package com.gekaradchenko.moviescheck.ui.splash.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.moviescheck.ui.movies.viewmodel.MoviesFragmentViewModel
import java.lang.IllegalArgumentException

class SplashScreenActivityViewModelFactory(
    val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenActivityViewModel::class.java)) {
            return SplashScreenActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}