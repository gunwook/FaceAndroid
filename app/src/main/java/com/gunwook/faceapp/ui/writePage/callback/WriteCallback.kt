package com.gunwook.faceapp.ui.writePage.callback

interface WriteCallback {

    interface View {
        fun doNext()
        fun doSave(title : String , tag : String, message : String)
        fun doToast(res : Int)
    }

    interface Presenter {
        fun checkData(title : String , tag : String, message : String)
        fun saveData(title : String , tag : String, message : String)
    }

}