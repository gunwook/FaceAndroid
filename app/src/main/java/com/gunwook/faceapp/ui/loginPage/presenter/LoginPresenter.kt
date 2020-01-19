package com.gunwook.faceapp.ui.loginPage.presenter

import android.content.Context
import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BasePresenter
import com.gunwook.faceapp.base.BaseUseCase
import com.gunwook.faceapp.callback.FetcherListener
import com.gunwook.faceapp.ext.isEmailValid
import com.gunwook.faceapp.ext.isPasswordValid
import com.gunwook.faceapp.ui.loginPage.callback.LoginCallback
import com.gunwook.faceapp.utils.CodeUtils
import com.gunwook.faceapp.utils.Logd
import com.gunwook.faceapp.utils.ParseUtils
import com.gunwook.faceapp.utils.PreferenceHelper
import kotlinx.android.synthetic.main.activity_sign_profile.view.*
import org.koin.core.inject

class LoginPresenter(var view : LoginCallback.View) : BasePresenter() , LoginCallback.Presenter {

    val usecase : BaseUseCase by inject()

    override fun checkLogin(email: String, password: String) {
        if(email.isEmailValid() && password.isPasswordValid()){
            view.doLogin()
        }else {
            view.doToast(R.string.check_failed)
        }
    }
    override fun doLogin(context : Context, email : String, password : String) {
        usecase.getLoginUseCase().doLogin(email , password , dispose) {
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

    override fun moveSign() {
        view.moveSignUpPage()
    }


}