package com.gunwook.faceapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.gunwook.faceapp.ui.loginPage.LoginActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import android.service.autofill.Validators.not
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches


import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginEspressoTest() {
    @get:Rule var activityTestRule = ActivityTestRule(LoginActivity::class.java)

    private val fetchingIdlingResource = FetchingIdlingResource()


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(fetchingIdlingResource)
        activityTestRule.activity.presenter.usecase.listener = fetchingIdlingResource
    }
    @Test
    fun textChange() {
        onView(withId(R.id.emailEt)).perform(typeText("test@gmail.com") , closeSoftKeyboard())
        onView(withId(R.id.passwordEt)).perform(typeText("test1234") , closeSoftKeyboard())
        onView(withId(R.id.loginTv)).perform(click())
    }

    @After
    fun release() {
        IdlingRegistry.getInstance().unregister(fetchingIdlingResource)
    }
}