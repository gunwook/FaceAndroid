package com.gunwook.faceapp.ui.emailCertifyPage.callback

interface EmailCallback {

    interface View {


    }

    interface Presenter {
        fun sendMail(email : String , subject : String , content : String)
    }

}