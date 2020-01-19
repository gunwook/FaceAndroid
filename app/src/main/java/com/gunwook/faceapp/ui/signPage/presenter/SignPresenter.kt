package com.gunwook.faceapp.ui.signPage.presenter

import android.content.Context
import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BasePresenter
import com.gunwook.faceapp.base.BaseUseCase
import com.gunwook.faceapp.ext.isEmailValid
import com.gunwook.faceapp.ext.isPasswordValid
import com.gunwook.faceapp.ui.signPage.callback.SignCallback
import com.gunwook.faceapp.utils.CodeUtils
import com.gunwook.faceapp.utils.Logd
import com.gunwook.faceapp.utils.ParseUtils
import com.gunwook.faceapp.utils.PreferenceHelper
import com.yanzhenjie.album.AlbumFile
import kotlinx.android.synthetic.main.activity_sign.view.*
import org.koin.core.inject


class SignPresenter(val view : SignCallback.View) : BasePresenter() , SignCallback.Presenter{

    var images : MutableList<AlbumFile> = mutableListOf()
    val usecase : BaseUseCase by inject()

    var email = ""
    var password = ""

    override fun checkAuthInvalid(email: String , password : String , passwordConfirm : String){
        if (email.isEmailValid() && password.isPasswordValid() && password == passwordConfirm) {
            view.doNext()
        }else {
            view.doToast(R.string.check_failed)

        }
    }

    override fun checkSign(fullName: String, nickName: String, gender: String, about: String) {
        if(images.isEmpty() || fullName.isEmpty() || nickName.isEmpty() || gender.isEmpty()){
            view.doToast(R.string.check_failed)
        }else {
            view.doAuth()
        }
    }
    override fun requestSign(context : Context , fullName: String, nickName: String, gender: String, about: String) {
        usecase.getSignUseCase().doSignUp(email , password , fullName , nickName , gender , about , images ,dispose) {
            it?.let {
                if(ParseUtils.getJson(it, CodeUtils.Parser.STATUS , -1) == 200) {
                    PreferenceHelper.setPref(context , CodeUtils.PreferenceCode.TOKEN ,
                        ParseUtils.getJson(it , CodeUtils.Parser.TOKEN , ""))
                    PreferenceHelper.setPref(context , CodeUtils.PreferenceCode.USER_ID , ParseUtils.getJson(it, CodeUtils.Parser.USER_ID , ""))
                    PreferenceHelper.setPref(context , CodeUtils.PreferenceCode.EMAIL , ParseUtils.getJson(it, CodeUtils.Parser.EMAIL , ""))
                    PreferenceHelper.setPref(context , CodeUtils.PreferenceCode.NAME , ParseUtils.getJson(it, CodeUtils.Parser.NAME , ""))


                    view.doNext()
                }
            }
        }
    }
}