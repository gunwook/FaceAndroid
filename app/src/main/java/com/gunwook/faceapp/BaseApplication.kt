package com.gunwook.faceapp

import android.app.Application
import com.gunwook.faceapp.base.BaseUseCase
import com.gunwook.faceapp.network.NetworkUtils
import com.gunwook.faceapp.utils.Logd
import com.gunwook.faceapp.utils.MediaLoader
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.io.IOException
import java.net.SocketException

class BaseApplication : Application() {

    private val modules = module {
        single { NetworkUtils() }
        single { BaseUseCase() }
    }


    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(modules)
        }


        RxJavaPlugins.setErrorHandler { e ->
            try {
                var error = e
                if (error is UndeliverableException) {
                    error = e.cause
                }
                if (error is IOException || error is SocketException) {
                    // fine, irrelevant network problem or API that throws on cancellation
                    return@setErrorHandler
                }
                if (error is InterruptedException) {
                    // fine, some blocking code was interrupted by a dispose call
                    return@setErrorHandler
                }
                if (error is NullPointerException || error is IllegalArgumentException) {
                    // that's likely a bug in the application
                    Thread.currentThread().uncaughtExceptionHandler
                        .uncaughtException(Thread.currentThread(), error)
                    return@setErrorHandler
                }
                if (error is IllegalStateException) {
                    // that's a bug in RxJava or in a custom operator
                    Thread.currentThread().uncaughtExceptionHandler
                        .uncaughtException(Thread.currentThread(), error)
                    return@setErrorHandler
                }

                Logd.w("Undeliverable exception received, not sure what to do")
            }catch (e : Exception){
                e.printStackTrace()
            }
        }


        Album.initialize(
            AlbumConfig.newBuilder(this)
                .setAlbumLoader(MediaLoader())
                .build()
        )
    }
}