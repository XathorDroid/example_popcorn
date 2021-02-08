package com.xathordroid.popcorn.retrofit

import com.xathordroid.popcorn.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {

    private val theMovieDBService: TheMovieDBService
    private val retrofit: Retrofit

    companion object {
        var instance: TheMovieDBClient? = null
            get() {
                if (field == null) {
                    instance = TheMovieDBClient()
                }
                return field
            }
    }

    init {
        // Add the interceptor
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        val client = okHttpClientBuilder.build()

        // Build the client with Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.TMDBAPI_BAASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        // Instance Retrofit service since retrofit object
        theMovieDBService = retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieDbService() = theMovieDBService
}