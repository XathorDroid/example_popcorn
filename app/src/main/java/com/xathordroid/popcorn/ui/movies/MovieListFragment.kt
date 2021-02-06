package com.xathordroid.popcorn.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xathordroid.popcorn.R
import com.xathordroid.popcorn.retrofit.models.Movie

class MovieListFragment : Fragment() {

    private lateinit var moviesViewModel: MovieViewModel
    private lateinit var moviesAdapter: MovieRecyclerViewAdapter
    private var popularMovies: List<Movie> = ArrayList()

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list_list, container, false)

        // instance the viewModel
        moviesViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        moviesAdapter = MovieRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = moviesAdapter
            }
        }

        // observer to load the popular-movies
        moviesViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
            moviesAdapter.setData(popularMovies)
        })

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}