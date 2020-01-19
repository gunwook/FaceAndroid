package com.gunwook.faceapp.ui.signProfilePagee

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BaseMVPActivity
import com.gunwook.faceapp.databinding.ActivitySignBinding
import com.gunwook.faceapp.databinding.ActivitySignProfileBinding
import com.gunwook.faceapp.ext.toast
import com.gunwook.faceapp.ui.mainPage.MainActivity
import com.gunwook.faceapp.ui.signPage.callback.SignCallback
import com.gunwook.faceapp.ui.signPage.presenter.SignPresenter
import com.gunwook.faceapp.utils.ActivityUtils
import com.gunwook.faceapp.utils.CodeUtils
import com.gunwook.faceapp.utils.GalleryUtils

class SignProfileAcitivity : BaseMVPActivity<ActivitySignProfileBinding, SignPresenter>() , SignCallback.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_sign_profile
    }

    override fun initView() {
        binding?.let { view ->
            presenter.email = intent.getStringExtra(CodeUtils.ActivityCode.EMAIL) ?: ""
            presenter.password = intent.getStringExtra(CodeUtils.ActivityCode.PASSWORD) ?: ""

            view.profileIv.setOnClickListener {
                GalleryUtils.startGallery(this) {
                    presenter.images = it

                    if(presenter.images.isNotEmpty()) {
                        Glide.with(this).load(it[0].thumbPath).centerCrop().apply(RequestOptions.circleCropTransform()).into(view.profileIv)
                    }
                }
            }

            view.signUpTv.setOnClickListener {
                presenter.checkSign(view.fullNameEt.text.toString() , view.nickNameEt.text.toString(),
                    view.genderEt.text.toString() , view.genderEt.text.toString())
            }
        }
    }

    override fun initPresenter(): SignPresenter {
        return SignPresenter(this)
    }

    override fun doNext() {
        ActivityUtils.startActivity(this, MainActivity::class.java , null)
    }

    override fun doToast(message: Int) {
        getString(message).toast(this)
    }

    override fun doAuth() {
        binding?.let { view ->
            presenter.requestSign(this , view.fullNameEt.text.toString() , view.nickNameEt.text.toString(),
                view.genderEt.text.toString() , view.genderEt.text.toString())
        }
    }
}