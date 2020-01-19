package com.gunwook.faceapp.base

import com.gunwook.faceapp.callback.FetcherListener
import com.gunwook.faceapp.network.NetworkHelper
import com.gunwook.faceapp.network.NetworkUtils
import com.gunwook.faceapp.ui.emailCertifyPage.usecase.EmailCertifyUseCase
import com.gunwook.faceapp.ui.feedPage.usecase.FeedUseCase
import com.gunwook.faceapp.ui.loginPage.usecase.LoginUseCase
import com.gunwook.faceapp.ui.signPage.usecase.SignUseCase
import com.gunwook.faceapp.ui.writePage.usecase.WriteUseCase
import com.gunwook.faceapp.utils.ParseUtils
import com.yanzhenjie.album.AlbumFile
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import org.json.JSONObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException

class BaseUseCase : KoinComponent {
    private val network : NetworkUtils by inject()
    var listener : FetcherListener? = null

    /**
     * 유저 권한 체크
     * */
    fun requestAuthUser(dispose : CompositeDisposable, callback : (isSuccess : Boolean) -> Unit) {
        dispose.add(network.getHelper().requestAuthUser()
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread())
            .doOnSubscribe { listener?.beginFetching() }
            .doFinally { listener?.doneFetching() }
            .subscribe({ res ->
                if (res.isSuccessful) {
                    callback(true)
                }else {
                    callback(false)
                }
            },{
                callback(false)
            }))
    }

    @Throws(IOException::class)
    fun getWriteUseCase() : WriteUseCase {
        return WriteUseCase()
    }

    @Throws(IOException::class)
    fun getLoginUseCase() : LoginUseCase {
        return LoginUseCase(listener)
    }

    @Throws(IOException::class)
    fun getSignUseCase() : SignUseCase {
        return SignUseCase(listener)
    }

    @Throws(IOException::class)
    fun getFeedUseCase() : FeedUseCase {
        return FeedUseCase()
    }

    @Throws(IOException::class)
    fun getEmailUseCase() : EmailCertifyUseCase {
        return EmailCertifyUseCase()
    }

}