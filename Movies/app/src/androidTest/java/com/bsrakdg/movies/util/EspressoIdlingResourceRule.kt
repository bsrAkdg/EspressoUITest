package com.bsrakdg.movies.util

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*
    Option 1 (the long way) :
    This option is much more difficult to read and is more verbose.
 */
// class EspressoIdlingResourceRule : TestRule {
//    override fun apply(base: Statement?, description: Description?): Statement {
//        return IdlingResourceStatement(base)
//    }
//
//    class IdlingResourceStatement(private val base: Statement?) : Statement() {
//        private val idlingResource = EspressoIdlingResource.countingIdlingResource
//
//        override fun evaluate() {
//            // @Before
//            IdlingRegistry.getInstance().register(idlingResource)
//            try {
//                base?.evaluate()
//                    ?: throw Exception("Error evaluating test. Base statement is null.")
//            } finally {
//                // @After
//                IdlingRegistry.getInstance().unregister(idlingResource)
//            }
//        }
// }
// }

/*
    Option 2 (the simple way) :
    Simplified version of option#1. (TextWatcher class implements TestRule)
*/
class EspressoIdlingResourceRule : TestWatcher() {
        private val idlingResource = EspressoIdlingResource.countingIdlingResource

    // @After
    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
        }

    // @Before
    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }
}
