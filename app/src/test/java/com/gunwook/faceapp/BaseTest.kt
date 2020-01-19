package com.gunwook.faceapp

import com.gunwook.faceapp.base.BaseUseCase
import com.gunwook.faceapp.network.NetworkUtils
import org.junit.AfterClass
import org.junit.BeforeClass
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

abstract class BaseTest : KoinTest {

    companion object : KoinComponent {

        val modules = module {
            single { NetworkUtils() }
            single { BaseUseCase() }
        }

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            startKoin{
                modules(modules)
            }
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            stopKoin()
        }
    }
}