package com.gunwook.faceapp.ui.mainPage.presenter

import android.app.Activity
import android.content.Context
import com.gunwook.faceapp.base.BasePresenter
import com.gunwook.faceapp.base.BaseUseCase
import com.gunwook.faceapp.ui.mainPage.callback.MainCallback
import com.gunwook.faceapp.utils.CodeUtils
import com.gunwook.faceapp.utils.GalleryUtils
import com.gunwook.faceapp.utils.PreferenceHelper
import org.koin.core.inject

class MainPresenter(val view : MainCallback.View) : BasePresenter() , MainCallback.Presenter {

    val usecase : BaseUseCase by inject()

    override fun showGallery(context : Activity) {
        if (PreferenceHelper.getPref(context, CodeUtils.PreferenceCode.TOKEN , "") == "") {
               view.moveLoginPage()
        }else {
            // 24 시간 뒤에는 토큰 만료!
            usecase.requestAuthUser(dispose) {
                if (it) {
                    GalleryUtils.startGallery(context) {
                        view.moveWritePage(it)
                    }
                }else {
                    view.moveLoginPage()
                }
            }
        }
    }
}