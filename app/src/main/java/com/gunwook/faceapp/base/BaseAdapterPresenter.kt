package com.gunwook.faceapp.base

interface BaseNavigator {
    fun getItemViewType(position : Int) : Int
    fun getItem(position : Int) : Any?
}

abstract class BaseAdapterPresenter :  BaseNavigator , BasePresenter() {



}