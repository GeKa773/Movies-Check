package com.gekaradchenko.moviescheck.ui.movieitem.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gekaradchenko.moviescheck.data.MovieItemData
import com.gekaradchenko.moviescheck.network.parseMovieItem
import kotlinx.coroutines.*

class MovieItemFragmentViewModel() : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)


    private val _item = MutableLiveData<MovieItemData>()
    val item: LiveData<MovieItemData> = _item

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    fun setImage(image: String) {
        _image.value = image
    }


    fun getMovieItem(url: String) {
        coroutineScope.launch {
            val movie = parseMovieItem(url)
            println(movie?.printItem())

            println(movie)
            Dispatchers.Main {
                _item.value = movie
            }
            Dispatchers.Main {

            }

        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}