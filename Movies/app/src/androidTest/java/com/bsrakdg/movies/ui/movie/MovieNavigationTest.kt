package com.bsrakdg.movies.ui.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bsrakdg.movies.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {

    @Test
    fun testMovieFragmentsNavigation() {

        // SETUP
        val activityScenario =
            ActivityScenario.launch(MainActivity::class.java) // start MovieDetailFragment

        // Nav DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click()) // open DirectorsFragment

        // VERIFY
        onView(withId(R.id.fragment_movie_directors_parent)).check(matches(isDisplayed())) // check

        pressBack() // go back MovieDetailFragment

        // VERIFY
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed())) // is displayed ??

        // Nav StarActorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click()) // open StarActorsFragment

        // VERIFY
        onView(withId(R.id.fragment_movie_actors_parent)).check(matches(isDisplayed())) // is displayed ??

    }
}