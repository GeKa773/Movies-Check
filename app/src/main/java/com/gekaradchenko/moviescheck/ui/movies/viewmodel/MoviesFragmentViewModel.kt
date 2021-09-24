package com.gekaradchenko.moviescheck.ui.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gekaradchenko.moviescheck.data.MovieData
import com.gekaradchenko.moviescheck.network.BASE_URL
import com.gekaradchenko.moviescheck.network.parseMoviesGrid
import kotlinx.coroutines.*
import org.jsoup.Jsoup


class MoviesFragmentViewModel() : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        getMoviesGrid()
    }

    private val _moviesList = MutableLiveData<List<MovieData>>()
    val moviesList: LiveData<List<MovieData>> = _moviesList


    private fun getMoviesGrid() {
        coroutineScope.launch {
            val array = parseMoviesGrid()
            Dispatchers.Main {
                _moviesList.value = array
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}