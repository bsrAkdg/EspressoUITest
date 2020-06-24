package com.bsrakdg.movies

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Test
    fun test_navSecondaryActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.button_next_activity)).perform(click()) // trigger start secondary activity start

        onView(withId(R.id.secondary)).check(matches(isDisplayed())) // is secondary exist ??
    }

    @Test
    fun test_backPress_toMainActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.button_next_activity)).perform(click()) // trigger start secondary activity start

        onView(withId(R.id.secondary)).check(matches(isDisplayed())) // is secondary exist ??

        onView(withId(R.id.button_back)).perform(click()) // trigger back button // method 1

        // pressBack() // method 2

        onView(withId(R.id.main)).check(matches(isDisplayed())) // is main exist ??
    }
}