package com.gekaradchenko.moviescheck.network

import android.util.Log
import com.gekaradchenko.moviescheck.data.MovieData
import org.jsoup.Jsoup


const val BASE_URL = "https://megogo.net"

const val RU = "/ru"
const val ENG = "/en"

const val FILMS = "/films"
const val SERIES = "/series"
const val MULT = "/mult"
const val PREMIERE = "/premiere"

const val FILTER_POPULARITY_DEFAULT = ""
const val FILTER_BY_NOVELTY = "/filter_latest"
const val FILTER_RECOMMENDATIONS = "/filter_recommended"

const val PAGINATION1 = "/pageToken_PjMw"
const val PAGINATION2 = "/pageToken_PjYw"
const val PAGINATION3 = "/pageToken_Pjkw"
const val PAGINATION4 = "/pageToken_PjEyMA=="


const val BASE_URL1 = "https://megogo.net/ru/films"

object Page{
    const val PAGE_1 =""
    const val PAGE_2 ="/pageToken_PjMw"
    const val PAGE_3 ="/pageToken_PjYw"
    const val PAGE_4 ="/pageToken_Pjkw"
    const val PAGE_5 ="/pageToken_PjEyMA=="
    const val PAGE_6 ="/pageToken_PjE1MA=="
    const val PAGE_7 ="/pageToken_PjE4MA=="
    const val PAGE_8 ="/pageToken_PjIxMA=="
    const val PAGE_9 ="/pageToken_PjI0MA=="
    const val PAGE_10 ="/pageToken_PjI3MA=="
    const val PAGE_11 ="/pageToken_PjMwMA=="
    const val PAGE_12 ="/pageToken_PjMzMA=="
}


suspend fun parseMoviesGrid(url: String): List<MovieData> {
    val array = ArrayList<MovieData>()
    try {

        val document = Jsoup.connect(url).get()
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


