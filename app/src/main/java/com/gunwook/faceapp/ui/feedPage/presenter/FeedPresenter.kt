package com.gunwook.faceapp.ui.feedPage.presenter

import com.gunwook.faceapp.base.BaseAdapterPresenter
import com.gunwook.faceapp.base.BasePresenter
import com.gunwook.faceapp.base.BaseUseCase
import com.gunwook.faceapp.ui.feedPage.callback.FeedCallback
import com.gunwook.faceapp.ui.feedPage.model.FeedModel
import com.gunwook.faceapp.utils.CodeUtils
import com.gunwook.faceapp.utils.Logd
import org.koin.core.inject

class FeedPresenter(val view : FeedCallback.View) : BaseAdapterPresenter() , FeedCallback.Presenter {

    val usecase : BaseUseCase by inject()
    private val listOfItems : MutableList<FeedModel> = mutableListOf()

    private var mPage = 0

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return listOfItems[position]
    }

    override fun getData() {
        usecase.getFeedUseCase().requestGetStory(mPage, CodeUtils.CommonCode.MAX_PAGE ,dispose) {
            if (it != null) {
                listOfItems.clear()
                listOfItems.addAll(it)


                view.notify(listOfItems)
            }
        }
    }
}