package com.bsrakdg.movies.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bsrakdg.movies.R
import com.bsrakdg.movies.data.FakeMovieData
import com.bsrakdg.movies.ui.movie.MoviesListAdapter.MovieViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @get: Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    val listItemInList = 4
    var movieInList = FakeMovieData.movies[listItemInList]

    /*
        Recyclerview comes into view
     */
    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /*
        Select list item, nav to DetailFragment
        Correct movie is in view?
     */
    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(listItemInList, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(movieInList.title)))
    }

    /*
        Select list item, nav to DetailFragment
        pressBack
     */
    @Test
    fun test_backNavigation_toMovieListFragment() {
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(listItemInList, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(movieInList.title)))

        pressBack()

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }


    /*
        Select list item, nav to DetailFragment
        select directors textView, nav to DirectorsFragment
     */
    @Test
    fun test_navDirectorsFragment_validateDirectorList() {
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(listItemInList, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(movieInList.title)))

        onView(withId(R.id.movie_directiors)).perform(click())

        val directors = movieInList.directors
        onView(withId(R.id.directors_text))
            .check(matches(withText(DirectorsFragment.stringBuilderForDirectors(directors!!))))
    }


    /*
        Select list item, nav to DetailFragment
        select starActors textView, nav to StarActorsFragment
     */
    @Test
    fun test_navStarActorsFragment_validateStarActorsList() {
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(listItemInList, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(movieInList.title)))

        onView(withId(R.id.movie_star_actors)).perform(click())

        val starActors = movieInList.star_actors
        onView(withId(R.id.star_actors_text))
            .check(matches(withText(StarActorsFragment.stringBuilderForStarActors(starActors!!))))
    }

}