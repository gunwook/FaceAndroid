package com.gunwook.faceapp.ui.emailCertifyPage

import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BaseMVPActivity
import com.gunwook.faceapp.databinding.ActivityEmailCertifyBinding
import com.gunwook.faceapp.databinding.ActivitySignBinding
import com.gunwook.faceapp.ui.emailCertifyPage.presenter.EmailCertifyPresenter
import com.gunwook.faceapp.ui.loginPage.presenter.LoginPresenter

class EmailCertifyActivity : BaseMVPActivity<ActivityEmailCertifyBinding, EmailCertifyPresenter>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_email_certify
    }

    override fun initView() {

    }

    override fun initPresenter(): EmailCertifyPresenter {
        return EmailCertifyPresenter()
    }
}