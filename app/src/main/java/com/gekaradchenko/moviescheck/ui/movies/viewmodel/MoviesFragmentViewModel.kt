package com.gekaradchenko.moviescheck.ui.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gekaradchenko.moviescheck.data.MovieData
import com.gekaradchenko.moviescheck.network.*
import kotlinx.coroutines.*
import org.jsoup.Jsoup


class MoviesFragmentViewModel() : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    val language = MutableLiveData("English")
    val type = MutableLiveData("Movies")
    val filter = MutableLiveData("By popularity")
    val genre = MutableLiveData("Any")

    private val _languageUrl = MutableLiveData<String>(RU)
    val languageUrl: LiveData<String> = _languageUrl

    private val _typeUrl = MutableLiveData<String>(FILMS)
    val typeUrl: LiveData<String> = _typeUrl

    private val _filterUrl = MutableLiveData<String>(FILTER_POPULARITY_DEFAULT)
    val filterUrl: LiveData<String> = _filterUrl

    private val _genreUrl = MutableLiveData<String>(MoviesConst.ANY)
    val genreUrl: LiveData<String> = _genreUrl

    private val _pageUrl = MutableLiveData<String>(Page.PAGE_1)
    val pageUrl: LiveData<String> = _pageUrl

    private val _baseUrl = MutableLiveData<String>(getURL())
    val baseUrl: LiveData<String> = _baseUrl

    private var page = 1
    private val maxPage = 12

    init {
        getMoviesGrid()
    }

    private val _moviesList = MutableLiveData<List<MovieData>>()
    val moviesList: LiveData<List<MovieData>> = _moviesList


    fun getMoviesGrid() {
        coroutineScope.launch {
            val array = parseMoviesGrid(baseUrl.value!!)
            Dispatchers.Main {
                _moviesList.value = array
                println("?????????????????????????")
            }
        }
        println("!!!!!!!!!!!!1")
    }

    fun getURL(): String {
        val url =
            "$BASE_URL${languageUrl.value}${typeUrl.value}${filterUrl.value}${genreUrl.value}${pageUrl.value}"
        return url
    }

    fun setBaseUrl() {
        _baseUrl.value = getURL()
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun setLanguage(id: Int) {
        _languageUrl.value = when (id) {
            0 -> RU
            1 -> ENG
            else -> ENG
        }

    }

    fun setType(id: Int) {
        setFilter(0)
        setGenre(0)
        _typeUrl.value = when (id) {
            0 -> FILMS
            1 -> SERIES
            2 -> MULT
            3 -> PREMIERE
            else -> FILMS
        }


    }

    fun setFilter(id: Int) {
        setGenre(0)
        _filterUrl.value = when (id) {
            0 -> FILTER_POPULARITY_DEFAULT
            1 -> FILTER_BY_NOVELTY
            2 -> FILTER_RECOMMENDATIONS
            else -> FILTER_POPULARITY_DEFAULT
        }

    }

    fun setGenre(id: Int) {
        _typeUrl.value?.let {
            _genreUrl.value = when (it) {
                FILMS -> when (id) {
                    0 -> MoviesConst.ANY
                    1 -> MoviesConst.COMEDY
                    2 -> MoviesConst.ACTION
                    3 -> MoviesConst.MYSTERY
                    4 -> MoviesConst.MELODRAMA
                    5 -> MoviesConst.THRILLER
                    6 -> MoviesConst.HORROR
                    7 -> MoviesConst.ADVENTURES
                    8 -> MoviesConst.SPORTS
                    9 -> MoviesConst.FICTION
                    10 -> MoviesConst.CRIME
                    11 -> MoviesConst.DRAMA
                    12 -> MoviesConst.BIOGRAPHY
                    13 -> MoviesConst.WAR
                    14 -> MoviesConst.HISTORICAL
                    15 -> MoviesConst.DOCUMENTARY
                    16 -> MoviesConst.FAMILY
                    17 -> MoviesConst.ANIME
                    18 -> MoviesConst.FOREIGN
                    19 -> MoviesConst.FANTASY
                    20 -> MoviesConst.COMICS
                    21 -> MoviesConst.MUSICAL
                    22 -> MoviesConst.WESTERN
                    23 -> MoviesConst.SHORT
                    24 -> MoviesConst.KIDS
                    25 -> MoviesConst.ART_HOUSE
                    26 -> MoviesConst.FOR_18
                    27 -> MoviesConst.ANIMATION
                    else -> MoviesConst.ANY
                }
                SERIES -> when (id) {
                    0 -> SeriesConst.ANY
                    1 -> SeriesConst.COMEDY
                    2 -> SeriesConst.ACTION
                    3 -> SeriesConst.MYSTERY
                    4 -> SeriesConst.MELODRAMA
                    5 -> SeriesConst.THRILLER
                    6 -> SeriesConst.HORROR
                    7 -> SeriesConst.ADVENTURES
                    8 -> SeriesConst.SPORTS
                    9 -> SeriesConst.FICTION
                    10 -> SeriesConst.CRIME
                    11 -> SeriesConst.DRAMA
                    12 -> SeriesConst.BIOGRAPHY
                    13 -> SeriesConst.WAR
                    14 -> SeriesConst.HISTORICAL
                    15 -> SeriesConst.DOCUMENTARY
                    16 -> SeriesConst.FAMILY
                    17 -> SeriesConst.ANIME
                    18 -> SeriesConst.FOREIGN
                    19 -> SeriesConst.FANTASY
                    20 -> SeriesConst.COMICS
                    else -> SeriesConst.ANY
                }
                MULT -> when (id) {
                    0 -> CartoonsConst.ANY
                    1 -> CartoonsConst.COMEDY
                    2 -> CartoonsConst.ADVENTURES
                    3 -> CartoonsConst.FICTION
                    4 -> CartoonsConst.FAMILY
                    5 -> CartoonsConst.ANINE
                    6 -> CartoonsConst.FOREIGN
                    7 -> CartoonsConst.FANTASY
                    8 -> CartoonsConst.COMICS
                    9 -> CartoonsConst.MUSICAL
                    10 -> CartoonsConst.WESTERN
                    11 -> CartoonsConst.SHORT
                    12 -> CartoonsConst.KIDS
                    13 -> CartoonsConst.FEATURE_FILM
                    14 -> CartoonsConst.CARTOON_SERIAL
                    15 -> CartoonsConst.SOJUZ_MULTFILM
                    16 -> CartoonsConst.FOR_THE_LITTLE_ONES
                    else -> CartoonsConst.ANY
                }
                PREMIERE -> PremieresCons.ANY
                else -> MoviesConst.ANY
            }
        }
    }

    fun setNextPage() {
        if (page < maxPage){
            page++

            _pageUrl.value = when(page){
                else ->Page.PAGE_1
            }
        }

    }
}