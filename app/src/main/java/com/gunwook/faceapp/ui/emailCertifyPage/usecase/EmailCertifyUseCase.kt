package com.gunwook.faceapp.ui.emailCertifyPage.usecase

import com.gunwook.faceapp.network.NetworkUtils
import com.gunwook.faceapp.ui.feedPage.model.FeedModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import org.koin.core.KoinComponent
import org.koin.core.inject

class EmailCertifyUseCase : KoinComponent {

    private val network : NetworkUtils by inject()

    fun requestGetStory(email : String , subject : String,  content : String , dispose : CompositeDisposable, callback : (obj : JSONObject?) -> Unit) {
        dispose.add(network.getHelper().requestSendMail(email, subject , content)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()).subscribe({ res ->
                val response = res.body()
                callback(response)
            },{
                callback(null)
            }))
    }
}