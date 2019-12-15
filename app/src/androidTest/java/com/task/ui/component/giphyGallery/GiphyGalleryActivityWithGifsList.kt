package com.task.ui.component.giphyGallery

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.task.DATA_STATUS
import com.task.R
import com.task.TestDataReprository
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class GiphyGalleryActivityWithGifsList {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(GiphyGalleryActivity::class.java)
    private var mIdlingResource: IdlingResource? = null
    @Before
    fun setup() {
        TestDataReprository.Instance.STATUS = DATA_STATUS.NO_INTERNET
        mIdlingResource = mActivityTestRule.activity.countingIdlingResource
        IdlingRegistry.getInstance().register(mIdlingResource)
    }

    @Test
    fun testNoInternet() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_images_list)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.tv_no_data)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pb_loading)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }
}