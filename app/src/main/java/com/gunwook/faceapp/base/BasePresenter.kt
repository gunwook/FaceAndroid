package com.gunwook.faceapp.base

import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent


interface BasePresenterCallback {
}

abstract class BasePresenter : BasePresenterCallback  , KoinComponent {
    val dispose : CompositeDisposable by lazy { CompositeDisposable() }

    fun attach() {
    }

    fun detach() {
        dispose.clear()
    }
}