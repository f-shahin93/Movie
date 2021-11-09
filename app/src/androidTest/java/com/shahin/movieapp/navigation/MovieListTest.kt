package com.shahin.movieapp.navigation

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shahin.movieapp.R
import com.shahin.movieapp.navigation.MyViewAction.Companion.withTitle
import com.shahin.movieapp.ui.MainActivity
import com.shahin.movieapp.ui.movielist.MovieListAdapter
import com.shahin.movieapp.ui.movielist.MovieListFragment
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieListTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigationToMovieDetailScreen() {
        // Create a TestNavHostController
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the MovieListScreen
        val movieListScenario = launchFragmentInContainer<MovieListFragment>(
            themeResId = R.style.Theme_MaterialComponents_DayNight_NoActionBar
        )

        movieListScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.navigation_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.recycler_list))
            .perform(actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(0, click()))
        MatcherAssert.assertThat(
            navController.currentDestination?.id, CoreMatchers.equalTo(R.id.navigation_movie_detail)
        )

    }

    @Test
    fun test_selectListItem() {
        // Click list item #LIST_ITEM_IN_TEST
        /*onView(withId(R.id.recycler_list))
            .perform(actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(0, click()))*/

        // custom Matcher
        val matcher: Matcher<RecyclerView.ViewHolder> = withTitle("Dune")
        onView(withId(R.id.recycler_list))
            .perform(RecyclerViewActions.actionOnHolderItem(matcher, click()))
    }

}

class MyViewAction {
    companion object {
        fun withTitle(title: String): Matcher<RecyclerView.ViewHolder> {
            return object :
                BoundedMatcher<RecyclerView.ViewHolder, MovieListAdapter.MovieViewHolder>(
                    MovieListAdapter.MovieViewHolder::class.java
                ) {
                override fun describeTo(description: Description?) {
                }

                override fun matchesSafely(item: MovieListAdapter.MovieViewHolder?): Boolean {
                    return item?.binding?.title.equals(title)
                }
            }
        }

        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? = null

                override fun getDescription(): String = "Click on a child view with specified id."

                override fun perform(uiController: UiController?, view: View?) {
                    val itemView: View? = view?.findViewById(id)
                    itemView?.performClick()
                }
            }
        }

    }
}