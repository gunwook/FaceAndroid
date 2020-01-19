package com.gunwook.faceapp

import com.gunwook.faceapp.network.NetworkUtils
import com.gunwook.faceapp.ui.signPage.callback.SignCallback
import com.gunwook.faceapp.ui.signPage.presenter.SignPresenter
import com.nhaarman.mockitokotlin2.verify
import com.yanzhenjie.album.AlbumFile
import org.junit.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito

class SignPresenterUnitTest : BaseTest(){
    lateinit var  mockView : SignCallback.View;
    lateinit var  presenter : SignPresenter;

    @Before
    fun setup() {
        mockView = Mockito.mock(SignCallback.View::class.java)

        presenter = SignPresenter(mockView)
    }

    @Test
    fun checkAuthInvalid() {
        presenter.checkAuthInvalid("test01@gmail.com", "test1234", "test1234")

        verify(mockView).doNext()
    }


    @Test
    fun requestSign() {
        presenter.images.add(AlbumFile())
        presenter.checkSign("leegunwook", "gunwook", "man" , "hello")

        verify(mockView).doAuth()
    }
}