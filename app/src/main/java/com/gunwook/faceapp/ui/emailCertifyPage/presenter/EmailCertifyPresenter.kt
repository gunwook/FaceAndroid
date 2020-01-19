package com.gunwook.faceapp.ui.emailCertifyPage.presenter

import com.gunwook.faceapp.base.BasePresenter
import com.gunwook.faceapp.base.BaseUseCase
import com.gunwook.faceapp.ui.emailCertifyPage.callback.EmailCallback
import org.koin.core.inject

class EmailCertifyPresenter : BasePresenter() , EmailCallback.Presenter {

    val usecase : BaseUseCase by inject()

    override fun sendMail(email: String, subject: String, content: String) {
        usecase.getEmailUseCase().requestGetStory(email , subject , content , dispose) {

        }
    }
}