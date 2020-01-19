package com.gunwook.faceapp.ui.writePage.usecase

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
import retrofit2.http.Multipart

class WriteUseCase : KoinComponent {

    private val network : NetworkUtils by inject()

    /**
     * 이야기 저장
     * @param title 제목
     * @param tag 태그
     * @param message 내용
     * @param images 이미지
     * */
    fun requestStorySave(title : String , tag : String, message : String , images : MutableList<AlbumFile> , dispose : CompositeDisposable , callback : (obj : JSONObject?) -> Unit) {
        val list : MutableList<MultipartBody.Part?> = mutableListOf()
        val date = NetworkHelper.convertStringToPart(System.currentTimeMillis().toString())
        val mTitle =  NetworkHelper.convertStringToPart(title)
        val mTag =  NetworkHelper.convertStringToPart(tag)
        val mConts  = NetworkHelper.convertStringToPart(message)
        images.forEach {
            if (it.path != null && it.path.isNotEmpty()) list.add(NetworkHelper.convertFileToPart(it.path))
        }

        dispose.add(network.getHelper().requestStorySave(date, mTitle , mTag, mConts, list )
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()).subscribe({ res ->
                val response = ParseUtils.responseParse(res)
                callback(response)
            },{
                callback(null)
            }))
    }
}