package com.gunwook.faceapp.ui.mainPage.callback

import android.app.Activity
import android.content.Context
import com.yanzhenjie.album.AlbumFile

interface MainCallback {

    interface View {
        fun moveWritePage(images : ArrayList<AlbumFile>)
        fun moveLoginPage()

    }

    interface Presenter{
        fun showGallery(context : Activity)

    }

}