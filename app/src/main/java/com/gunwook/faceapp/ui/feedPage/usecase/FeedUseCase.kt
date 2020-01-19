package com.gunwook.faceapp.ui.feedPage.usecase

import com.gunwook.faceapp.network.NetworkHelper
import com.gunwook.faceapp.network.NetworkUtils
import com.gunwook.faceapp.ui.feedPage.model.FeedModel
import com.yanzhenjie.album.AlbumFile
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import org.json.JSONArray
import org.json.JSONObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException

class FeedUseCase : KoinComponent {

    private val network : NetworkUtils by inject()

    fun requestGetStory(page : Int , maxCount : Int , dispose : CompositeDisposable, callback : (obj : List<FeedModel>?) -> Unit) {
        dispose.add(network.getHelper().requestGetStory(page, maxCount )
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