package com.xathordroid.popcorn.retrofit

import com.xathordroid.popcorn.retrofit.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {

    @GET("/movie/popular")
    fun getPopularMovies(): Call<PopularMoviesResponse>
}