package com.gekaradchenko.moviescheck.ui.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gekaradchenko.moviescheck.data.MovieData
import com.gekaradchenko.moviescheck.network.BASE_URL
import kotlinx.coroutines.*
import org.jsoup.Jsoup


class MoviesFragmentViewModel() : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        getSite()
    }

    private val _moviesList = MutableLiveData<List<MovieData>>()
    val moviesList: LiveData<List<MovieData>> = _moviesList


    private fun getSite() {
        val array = ArrayList<MovieData>()
        coroutineScope.launch {
            try {
                val document = Jsoup.connect(BASE_URL).get()
                val movieGrid = document.getElementsByClass("cards-list videos-list")
                val movieItems = movieGrid.first()?.children()
                movieItems?.forEachIndexed { index, element ->
                    val href = element.getElementsByTag("a").first()?.absUrl("href").toString()
                    val title = element.getElementsByClass("video-title card-content-title").text()
                    val image =
                        element.getElementsByTag("img").first()?.absUrl("data-original").toString()
                    val year = element.getElementsByClass("video-year").text()
                    val genre = element.getElementsByClass("video-country").text()

                    array.add(MovieData(index, href, image, title, year, genre))

                    Log.i(
                        "MoviesFragmentViewModel",
                        "title = $title,year = $year, genre =$genre image = $image, href = $href"
                    )
                }
                Dispatchers.Main {
                    _moviesList.value = array
                }
            } catch (e: Exception) {
                Log.i("MoviesFragmentViewModel", " exeption = ${e.message}")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}