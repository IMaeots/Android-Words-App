package com.example.wordsapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NavigationTests {

    lateinit var navController: TestNavHostController
    lateinit var letterListScenario: FragmentScenario<LetterListFragment>

    // Navigation controller and fragment setup
    @Before
    fun setup() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        letterListScenario = launchFragmentInContainer(
            themeResId =
            R.style.Theme_Words
        )

        letterListScenario.onFragment { fragment ->

            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    // Navigation to position and check
    @Test
    fun navigate_to_words_nav_component_2() {
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
    }

    @Test
    fun navigate_to_words_nav_component_3() {
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
    }
}