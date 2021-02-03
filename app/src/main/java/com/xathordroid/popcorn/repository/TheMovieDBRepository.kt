package com.xathordroid.popcorn.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.xathordroid.popcorn.common.MyApp
import com.xathordroid.popcorn.retrofit.TheMovieDBClient
import com.xathordroid.popcorn.retrofit.TheMovieDBService
import com.xathordroid.popcorn.retrofit.models.Movie
import com.xathordroid.popcorn.retrofit.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDBRepository {

    var theMovieDBService: TheMovieDBService? = null
    var theMovieDBClient: TheMovieDBClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null

    init {
        theMovieDBClient = TheMovieDBClient.instance
        theMovieDBService = theMovieDBClient?.getTheMovieDbService()
        popularMovies = popularMovies()
    }

    fun popularMovies(): MutableLiveData<List<Movie>>? {
        if (popularMovies == null) {
            popularMovies = MutableLiveData<List<Movie>>()
        }

        val call: Call<PopularMoviesResponse>? = theMovieDBService?.getPopularMovies()
        call?.enqueue(object : Callback<PopularMoviesResponse> {
            override fun onResponse(call: Call<PopularMoviesResponse>, response: Response<PopularMoviesResponse>) {
                if (response.isSuccessful) {
                    popularMovies?.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })

        return popularMovies
    }
}