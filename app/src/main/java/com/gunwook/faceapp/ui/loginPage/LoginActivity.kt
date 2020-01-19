package com.gunwook.faceapp.ui.loginPage

import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BaseMVPActivity
import com.gunwook.faceapp.databinding.ActivityLoginBinding
import com.gunwook.faceapp.ext.toast
import com.gunwook.faceapp.ui.loginPage.callback.LoginCallback
import com.gunwook.faceapp.ui.loginPage.presenter.LoginPresenter
import com.gunwook.faceapp.ui.mainPage.MainActivity
import com.gunwook.faceapp.ui.signPage.SignActivity
import com.gunwook.faceapp.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : BaseMVPActivity<ActivityLoginBinding , LoginPresenter>() , LoginCallback.View {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        binding?.let {
            it.loginTv.setOnClickListener {
                presenter.checkLogin( binding?.emailEt?.text.toString() , binding?.passwordEt?.text.toString())
            }


            it.signUpTv.setOnClickListener {
                presenter.moveSign()
            }
        }
    }


    override fun initPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }


    override fun moveSignUpPage() {
        ActivityUtils.startActivity(this, SignActivity::class.java , null)
    }

    override fun doNext() {
        ActivityUtils.startActivity(this, MainActivity::class.java , null)
    }

    override fun doLogin() {
        presenter.doLogin(this , binding?.emailEt?.text.toString() , binding?.passwordEt?.text.toString())
    }

    override fun doToast(id: Int) {
        getString(id).toast(this)
    }
}