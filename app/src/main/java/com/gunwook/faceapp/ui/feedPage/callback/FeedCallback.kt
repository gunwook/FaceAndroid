package com.gunwook.faceapp.ui.feedPage.callback

import com.gunwook.faceapp.ui.feedPage.model.FeedModel

interface FeedCallback {

    interface View {
        fun notify(list : List<FeedModel>)
    }

    interface Presenter {
        fun getData()

    }

}