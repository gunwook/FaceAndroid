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
import com.gunwook.faceapp.ui.signPage.SignActivity
import com.gunwook.faceapp.utils.CodeUtils


import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import android.content.Intent
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import com.gunwook.faceapp.ui.signProfilePagee.SignProfileAcitivity
import com.yanzhenjie.album.AlbumFile


@RunWith(AndroidJUnit4ClassRunner::class)
class SignProfileEspressoTest() {
    @get:Rule var activityTestRule = ActivityTestRule(SignProfileAcitivity::class.java)

    private val fetchingIdlingResource = FetchingIdlingResource()


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(fetchingIdlingResource)
        Intent().apply {
            this.putExtra(CodeUtils.ActivityCode.EMAIL , "${System.currentTimeMillis()}@gmail.com")
            this.putExtra(CodeUtils.ActivityCode.PASSWORD , "${System.currentTimeMillis()}")
            activityTestRule.launchActivity(this)
        }


        activityTestRule.activity.presenter.usecase.listener = fetchingIdlingResource

        activityTestRule.activity.presenter.images.add(AlbumFile())
    }
    @Test
    fun textChange() {
        onView(withId(R.id.fullNameEt)).perform(typeText("fullname"), closeSoftKeyboard())
        onView(withId(R.id.nickNameEt)).perform(typeText("nickname") , closeSoftKeyboard())
        onView(withId(R.id.genderEt)).perform(typeText("man") , closeSoftKeyboard())
        onView(withId(R.id.aboutEt)).perform(typeText("about") , closeSoftKeyboard())

        onView(withId(R.id.signUpTv)).perform(click())
    }

    @After
    fun release() {
        IdlingRegistry.getInstance().unregister(fetchingIdlingResource)
    }
}