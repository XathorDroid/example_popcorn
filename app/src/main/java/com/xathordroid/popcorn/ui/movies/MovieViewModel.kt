package com.xathordroid.popcorn.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xathordroid.popcorn.repository.TheMovieDBRepository
import com.xathordroid.popcorn.retrofit.models.Movie

class MovieViewModel: ViewModel() {

    private var theMovieDBRepository: TheMovieDBRepository
    private var popularMovies: LiveData<List<Movie>>

    init {
        theMovieDBRepository = TheMovieDBRepository()
        popularMovies = theMovieDBRepository?.popularMovies()!!
    }

    fun getPopularMovies(): LiveData<List<Movie>> {
        return popularMovies
    }
}