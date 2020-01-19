package com.gunwook.faceapp.ui.loginPage.usecase

import com.gunwook.faceapp.callback.FetcherListener
import com.gunwook.faceapp.network.NetworkUtils
import com.gunwook.faceapp.utils.ParseUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginUseCase(val listener: FetcherListener?) : KoinComponent {
    private val network : NetworkUtils by inject()

    /**
     * 로그인
     * @param email 이메일
     * @param password 패스워드
     * */
    fun doLogin(email : String, password : String, dispose: CompositeDisposable, callback : (obj : JSONObject?) -> Unit) {
        dispose.add(network.getHelper().requestPostLogin(email , password)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread())
            .doOnSubscribe {
                listener?.beginFetching()
            }
            .doFinally {
                    listener?.doneFetching()
            }
            .subscribe({ res ->
                val response = ParseUtils.responseParse(res)
                callback(response)
            },{
                callback(null)
            }))
    }

}