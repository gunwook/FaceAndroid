package com.gunwook.faceapp

import android.os.Build
import android.os.Handler
import android.os.Looper
import com.gunwook.faceapp.ext.toEditable
import com.gunwook.faceapp.ui.loginPage.LoginActivity
import com.gunwook.faceapp.ui.loginPage.callback.LoginCallback
import com.gunwook.faceapp.ui.signPage.callback.SignCallback
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.objectweb.asm.Handle
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
class LoginActivityUnitTest {

    lateinit var  mockView : LoginCallback.View;
    lateinit var activity : LoginActivity

    @Before
    fun setUp() {
        mockView = Mockito.mock(LoginCallback.View::class.java)

        activity = Robolectric.buildActivity(LoginActivity::class.java)
            .create()
            .resume()
            .get()

        activity.presenter.view = mockView
    }

    @Test
    fun loginTest() {
        activity.binding?.emailEt?.text = "test01@gmail.com".toEditable()
        activity.binding?.passwordEt?.text = "test1234".toEditable()


        activity.binding?.loginTv?.performClick()

        verify(mockView).doLogin()

    }
}