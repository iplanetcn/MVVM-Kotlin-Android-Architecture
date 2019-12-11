package com.task.ui.component.productImages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.task.R
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ImageGalleryActivityTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(ImageGalleryActivity::class.java)
    private var mIdlingResource: IdlingResource? = null
    @Before
    fun setup() {
        mIdlingResource = mActivityTestRule.activity.countingIdlingResource
        IdlingRegistry.getInstance().register(mIdlingResource)
    }


    @Test
    fun testScroll() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_images_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.iv_product_full_Image)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testRefresh() {
        //Before refresh there is a list .
        Espresso.onView(ViewMatchers.withId(R.id.rv_images_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pb_loading)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        // do refresh .
        Espresso.onView(ViewMatchers.withId(R.id.ic_toolbar_refresh)).perform(ViewActions.click())
        //after refresh there is a list.
        Espresso.onView(ViewMatchers.withId(R.id.rv_images_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pb_loading)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun displayNewsData() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_images_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pb_loading)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }
}