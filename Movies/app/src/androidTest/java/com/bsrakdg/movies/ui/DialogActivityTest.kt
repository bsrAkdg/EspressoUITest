package com.bsrakdg.movies.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bsrakdg.movies.R
import com.bsrakdg.movies.ui.DialogActivity.Companion.buildToastMessage
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DialogActivityTest {

    @Test
    fun test_showDialog_captureNameInput() {

        // GIVEN
        val activityScenario = ActivityScenario.launch(DialogActivity::class.java)
        val expectedName = "Busra"

        // Execute and Verify
        onView(withId(R.id.button_launch_dialog)).perform(click())
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed())) // is dialog text displayed?

        onView(withText(R.string.text_ok)).perform(click())

       // make sure dialog is still visible (can't click ok without entering a name)
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        // enter some input
        // set expectedName to material dialog editText with using typeText  (dialog editText id : md_input_message)
        onView(withId(R.id.md_input_message)).perform(typeText(expectedName))
        onView(withText(R.string.text_ok)).perform(click())

        // make sure dialog is gone (if view there isn't in the screen you should use doesNotExist when checked)
        onView(withText(R.string.text_enter_name)).check((doesNotExist()))

        // confirm name is set to TextView in activity
        onView(withId(R.id.text_name)).check(matches(withText(expectedName)))

        // test if toast is displayed
        onView(withText(buildToastMessage(expectedName)))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}