package com.gekaradchenko.moviescheck.network

import android.util.Log
import com.gekaradchenko.moviescheck.data.MovieData
import org.jsoup.Jsoup


const val BASE_URL = "https://megogo.net"

const val RU = "/ru"
const val ENG = "/en"

const val FILMS = "/films"
const val MULT = "/mult"
const val SERIES = "/series"
const val PREMIERE = "/premiere"

const val FILTER_POPULARITY_DEFAULT = ""
const val FILTER_BY_NOVELTY = "/filter_latest"
const val FILTER_RECOMMENDATIONS = "/filter_recommended"

const val PAGINATION1 = "/pageToken_PjMw"
const val PAGINATION2 = "/pageToken_PjYw"
const val PAGINATION3 = "/pageToken_Pjkw"
const val PAGINATION4 = "/pageToken_PjEyMA=="


const val BASE_URL1 = "https://megogo.net/ru/films"


suspend fun parseMoviesGrid(): List<MovieData> {
    val array = ArrayList<MovieData>()
    try {

        val document = Jsoup.connect("$BASE_URL1").get()
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

    } catch (e: Exception) {
        Log.i("MoviesFragmentViewModel", " exeption = ${e.message}")
    }

    return array
}


