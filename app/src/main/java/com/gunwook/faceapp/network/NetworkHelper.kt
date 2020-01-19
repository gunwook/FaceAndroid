package com.gunwook.faceapp.network

import com.gunwook.faceapp.utils.CodeUtils
import com.gunwook.faceapp.utils.Logd
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.File
import java.lang.Exception

object NetworkHelper {

    fun checkAuth(callback : (obj : JSONObject?) -> Unit , error : Throwable) {
//        (error as? HttpException)?.code()?.let {
//            JSONObject().apply {
//                this.put()
//            }
//            callback(JSONObject(err))
//            return
//        }
    }

    fun convertFileToPart(filePath : String) : MultipartBody.Part?{
        try {
            File(filePath).apply {
                val reqBody = RequestBody.create(MediaType.parse("image/*"), this)

                return MultipartBody.Part.createFormData(CodeUtils.Network.Params.FILES, this.name, reqBody)
            }
        }catch (e : Exception){
            Logd.e(e)
            return null
        }
    }

    fun convertStringToPart(data : String) : RequestBody? {
        return try {
            RequestBody.create(MediaType.parse("text/plain"), data);
        }catch (e : Exception){
            Logd.e(e)
            null
        }
    }
}