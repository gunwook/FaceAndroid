package com.gunwook.faceapp.network

import com.gunwook.faceapp.ui.feedPage.model.FeedModel
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

class MasterHelper(private var masterApi : MasterApi) {

    @Throws(IOException::class)
    fun requestPostLogin(email : String , password : String) : Single<ResponseBody> {
        return masterApi.postLogin(email,password, "")
    }
    @Throws(IOException::class)
    fun requestPostSignUp(email : RequestBody? , password : RequestBody? , fullName : RequestBody? , nickName : RequestBody? , gender : RequestBody? , about : RequestBody?  , images :  List<MultipartBody.Part?>)  : Single<ResponseBody>  {
        return masterApi.postSignUp(email, password , fullName, nickName, gender , about , images)
    }

    @Throws(IOException::class)
    fun requestStorySave(storyDate : RequestBody? , title : RequestBody?, tag : RequestBody?, message : RequestBody?, images :  List<MultipartBody.Part?>) : Single<ResponseBody> {
        return masterApi.postStory(images,storyDate,title,tag,message)
    }


    @Throws(IOException::class)
    fun requestAuthUser() : Single<Response<JSONObject>> {
        return masterApi.authUser()
    }

    @Throws(IOException::class)
    fun requestSendMail(email : String , subject : String , content : String) : Single<Response<JSONObject>> {
        return masterApi.sendMail(email , subject , content)
    }


    @Throws(IOException::class)
    fun requestGetStory(page : Int , count : Int)  : Single<Response<List<FeedModel>>> {
        return masterApi.getStory(page , count)

    }


}