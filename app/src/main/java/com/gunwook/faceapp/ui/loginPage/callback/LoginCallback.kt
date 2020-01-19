package com.gunwook.faceapp.ui.loginPage.callback

import android.content.Context

interface LoginCallback {

    interface View {
        fun moveSignUpPage()
        fun doLogin()
        fun doToast(id : Int)
        fun doNext()
    }

    interface Presenter{
        fun checkLogin(email : String , password : String)
        fun doLogin(context : Context, email : String, password : String)
        fun moveSign()
    }


}