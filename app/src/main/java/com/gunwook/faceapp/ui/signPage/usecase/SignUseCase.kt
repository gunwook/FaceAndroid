package com.gunwook.faceapp.ui.signPage.usecase

import com.gunwook.faceapp.callback.FetcherListener
import com.gunwook.faceapp.network.NetworkHelper
import com.gunwook.faceapp.network.NetworkUtils
import com.gunwook.faceapp.utils.ParseUtils
import com.yanzhenjie.album.AlbumFile
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import org.json.JSONObject
import org.koin.core.KoinComponent
import org.koin.core.inject

class SignUseCase(val listener: FetcherListener?) : KoinComponent {

    val network : NetworkUtils by inject()

    fun doSignUp(email : String, password : String, fullName: String, nickName: String, gender: String, about: String, images : List<AlbumFile>, dispose: CompositeDisposable, callback : (obj : JSONObject?) -> Unit) {
        val list : MutableList<MultipartBody.Part?> = mutableListOf()
        val mEmail =  NetworkHelper.convertStringToPart(email)
        val mPassword = NetworkHelper.convertStringToPart(password)
        val mFullName =  NetworkHelper.convertStringToPart(fullName)
        val mNickName =  NetworkHelper.convertStringToPart(nickName)
        val mGender = NetworkHelper.convertStringToPart(gender)
        val mAbout = NetworkHelper.convertStringToPart(about)

        images.forEach {
            if (it.path != null && it.path.isNotEmpty()) list.add(NetworkHelper.convertFileToPart(it.path))
        }


        dispose.add(network.getHelper().requestPostSignUp(mEmail, mPassword , mFullName , mNickName , mGender , mAbout , list)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                listener?.beginFetching()
            }
            .doFinally {
                listener?.doneFetching()
            }
            .observeOn(
            AndroidSchedulers.mainThread()).subscribe({ res ->
            val response = ParseUtils.responseParse(res)
            callback(response)
        },{
            callback(null)
        }))
    }
}