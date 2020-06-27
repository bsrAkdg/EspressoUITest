package com.bsrakdg.movies.util

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/*
    Option 1 (the long way) :
    This option is much more difficult to read and is more verbose.
 */
class EspressoIdlingResourceRule : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return IdlingResourceStatement(base)
    }

    class IdlingResourceStatement(private val base: Statement?) : Statement() {
        private val idlingResource = EspressoIdlingResource.countingIdlingResource

        override fun evaluate() {
            // @Before
            IdlingRegistry.getInstance().register(idlingResource)
            try {
                base?.evaluate()
                    ?: throw Exception("Error evaluating test. Base statement is null.")
            } finally {
                // @After
                IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }


    }
}