package com.gunwook.faceapp.ui.signPage

import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BaseMVPActivity
import com.gunwook.faceapp.databinding.ActivitySignBinding
import com.gunwook.faceapp.ext.toast
import com.gunwook.faceapp.ui.signPage.callback.SignCallback
import com.gunwook.faceapp.ui.signPage.presenter.SignPresenter
import com.gunwook.faceapp.ui.signProfilePagee.SignProfileAcitivity
import com.gunwook.faceapp.utils.ActivityUtils
import com.gunwook.faceapp.utils.CodeUtils

class SignActivity : BaseMVPActivity<ActivitySignBinding, SignPresenter>() , SignCallback.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_sign
    }

    override fun initView() {
        binding?.let { view ->
            view.signUpTv.setOnClickListener {
                presenter.checkAuthInvalid(view.emailEt.text.toString(),view.passwordEt.text.toString() , view.passwordConfirmEt.text.toString())
            }
        }
    }

    override fun initPresenter(): SignPresenter {
        return SignPresenter(this)
    }

    override fun doNext() {
        val map = HashMap<String, Any>()

        map.put(CodeUtils.ActivityCode.EMAIL , binding?.emailEt?.text.toString())
        map.put(CodeUtils.ActivityCode.PASSWORD , binding?.passwordEt?.text.toString())

        ActivityUtils.startActivity(this,SignProfileAcitivity::class.java , map)
    }

    override fun doToast(message: Int) {
        getString(message).toast(this)
    }

    override fun doAuth() {

    }
}