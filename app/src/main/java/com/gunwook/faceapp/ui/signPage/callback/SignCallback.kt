package com.gunwook.faceapp.ui.signPage.callback

import android.content.Context

interface SignCallback {

    interface View {
        fun doNext()
        fun doAuth()
        fun doToast(message : Int)
    }

    interface Presenter {
        fun checkAuthInvalid(email: String , password : String , passwordConfirm : String)
        fun checkSign(fullName : String , nickName : String , gender : String , about : String)
        fun requestSign(context : Context, fullName : String, nickName : String, gender : String, about : String)


    }


}