package com.gunwook.faceapp.ui.writePage.presenter

import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BasePresenter
import com.gunwook.faceapp.base.BaseUseCase
import com.gunwook.faceapp.network.NetworkUtils
import com.gunwook.faceapp.ui.writePage.callback.WriteCallback
import com.gunwook.faceapp.utils.ParseUtils
import com.yanzhenjie.album.AlbumFile
import org.koin.core.inject

class WritePresenter(var view : WriteCallback.View) : BasePresenter() , WriteCallback.Presenter {

     var imageList : MutableList<AlbumFile> = mutableListOf()
     val usecase : BaseUseCase by inject()

     override fun checkData(title: String, tag: String, message: String) {
          if (title.isNotEmpty() && tag.isNotEmpty() && message.isNotEmpty()) {
               view.doSave(title ,tag , message)
          }else {
               view.doToast(R.string.check_failed)
          }
     }

     override fun saveData(title : String, tag : String, message : String) {
          usecase.getWriteUseCase().requestStorySave(title , tag , message , imageList , dispose){
               if (it != null) {
                    view.doNext()
               }else {
                    view.doToast(R.string.network_failed)
               }
          }
     }


}