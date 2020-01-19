package com.gunwook.faceapp.utils

import com.gunwook.faceapp.ui.writePage.model.WriteModel
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject

object ParseUtils {

    fun responseParse(response : ResponseBody?) : JSONObject?{
        if (response == null) return null
        try {
            return JSONObject(response.string())
        }catch (e : Exception){
            Logd.e(e.message ?: "parse ERROR")
        }

        return null
    }
    fun parseToWriteModel(data : JSONObject) : WriteModel {

        val _imgs = getJson(data , CodeUtils.Parser.STORY_IMGS , JSONArray())

        val imgs : MutableList<String> = mutableListOf()

        _imgs?.let {
            for(i in 0 .. _imgs.length()){
                imgs.add(_imgs.optString(i))
            }
        }

        return WriteModel(
            getJson(data , CodeUtils.Parser.STORY_ID , 0L) ?: 0,
            imgs,
            getJson(data , CodeUtils.Parser.STORY_DATE , "") ?: "",
            getJson(data , CodeUtils.Parser.STORY_TITLE , "") ?: "",
            getJson(data , CodeUtils.Parser.STORY_TAG , "") ?: "",
            getJson(data , CodeUtils.Parser.STORY_MESSAGE , "") ?: "",
            getJson(data , CodeUtils.Parser.STORY_VISIBLE , "") ?: ""
        )
    }

    inline fun <reified T> getJson(data: JSONObject, key: String, defaultValue: T? = null): T? {
        try {
            return when (T::class) {
                String::class -> data.getString(key) as T?
                Int::class -> data.getInt(key) as T?
                Boolean::class -> data.getBoolean(key) as T?
                Double::class -> data.getDouble(key) as T?
                Long::class -> data.getLong(key) as T?
                JSONObject::class -> data.getJSONObject(key) as T?
                JSONArray::class -> data.getJSONArray(key) as T?
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        } catch (e: java.lang.Exception) {
            Logd.e(e.message ?: "")
        }

        return defaultValue
    }

}