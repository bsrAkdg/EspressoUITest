package com.bsrakdg.movies.factory

import androidx.fragment.app.FragmentFactory
import com.bsrakdg.movies.ui.movie.DirectorsFragment
import com.bsrakdg.movies.ui.movie.MovieDetailFragment
import com.bsrakdg.movies.ui.movie.StarActorsFragment

class MovieFragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {

            MovieDetailFragment::class.java.name -> {
                MovieDetailFragment()
            }

            DirectorsFragment::class.java.name -> {
                DirectorsFragment()
            }

            StarActorsFragment::class.java.name -> {
                StarActorsFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }


}

