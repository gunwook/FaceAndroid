package com.gunwook.faceapp.network

import com.gunwook.faceapp.ui.feedPage.model.FeedModel
import com.gunwook.faceapp.utils.CodeUtils
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface MasterApi {

    @FormUrlEncoded
    @POST(CodeUtils.Network.Api.LOGIN)
    fun postLogin(
        @Field(CodeUtils.Network.Params.USER_EMAIL) email : String,
        @Field(CodeUtils.Network.Params.USER_PASSWORD) password : String,
        @Field(CodeUtils.Network.Params.PUSH_ID) push_id : String
    ) : Single<ResponseBody>


    @GET(CodeUtils.Network.Api.STORY)
    fun getStory(
        @Query(CodeUtils.Network.Params.PAGE)  page : Int,
        @Query(CodeUtils.Network.Params.LIMIT) count : Int
    ) : Single<Response<List<FeedModel>>>

    @Multipart
    @POST(CodeUtils.Network.Api.STORY)
    fun postStory(
        @Part files : List<MultipartBody.Part?>,
        @Part(CodeUtils.Network.Params.STORY_DATE) story_date : RequestBody?,
        @Part(CodeUtils.Network.Params.STORY_TITLE) story_title : RequestBody?,
        @Part(CodeUtils.Network.Params.STORY_TAG) story_tag : RequestBody?,
        @Part(CodeUtils.Network.Params.STORY_MESSAGE) story_message : RequestBody?
        ) : Single<ResponseBody>

    @GET(CodeUtils.Network.Api.AUTH)
    fun authUser() : Single<Response<JSONObject>>

    @FormUrlEncoded
    @POST(CodeUtils.Network.Api.EMAIL)
    fun sendMail(
        @Field(CodeUtils.Network.Params.EMAIL) email : String,
        @Field(CodeUtils.Network.Params.SUBJECT) subject : String,
        @Field(CodeUtils.Network.Params.CONTENT) content : String
    ) : Single<Response<JSONObject>>



    @Multipart
    @POST(CodeUtils.Network.Api.SIGN)
    fun postSignUp(
        @Part(CodeUtils.Network.Params.EMAIL) email : RequestBody?,
        @Part(CodeUtils.Network.Params.PASSWORD) password : RequestBody?,
        @Part(CodeUtils.Network.Params.NAME)  name : RequestBody?,
        @Part(CodeUtils.Network.Params.NICKNAME) nickname : RequestBody?,
        @Part(CodeUtils.Network.Params.GENDER) gender : RequestBody?,
        @Part(CodeUtils.Network.Params.MESSAGE) message : RequestBody?,
        @Part files : List<MultipartBody.Part?>
        ) : Single<ResponseBody>

}