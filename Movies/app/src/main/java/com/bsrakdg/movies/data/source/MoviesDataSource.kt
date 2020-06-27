package com.bsrakdg.movies.data.source

import com.bsrakdg.movies.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?
    fun getMovies(): List<Movie>
}