package com.gunwook.faceapp.network

import android.content.Context
import androidx.annotation.NonNull
import com.gunwook.faceapp.utils.CodeUtils
import com.gunwook.faceapp.utils.PreferenceHelper
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetworkUtils() : KoinComponent {

    private val context : Context by inject()

    @Throws(IOException::class)
    private fun provideRetrofit(@NonNull okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(CodeUtils.Network.MASTER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    @Throws(IOException::class)
    private fun provideOkHttpClient() : OkHttpClient {
        //val interceptor = HttpLoggingInterceptor()

        //interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY // logging

        val headerInterceptor = Interceptor {
            val newRequeset = it.request().newBuilder().addHeader(CodeUtils.PreferenceCode.TOKEN, PreferenceHelper.getPref(context, CodeUtils.PreferenceCode.TOKEN, "") ?: "")
                .addHeader(CodeUtils.PreferenceCode.EMAIL, PreferenceHelper.getPref(context, CodeUtils.PreferenceCode.EMAIL, "") ?: "")
                .addHeader(CodeUtils.PreferenceCode.USER_ID, PreferenceHelper.getPref(context, CodeUtils.PreferenceCode.USER_ID, "") ?: "")
                .build()
            it.proceed(newRequeset)
        }


        return OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .build()
    }
    @Throws(IOException::class)
    private fun getApiCallInterface(retrofit : Retrofit) : MasterApi {
        return retrofit.create(MasterApi::class.java)
    }

    @Throws(IOException::class)
    fun getHelper() : MasterHelper {
        return MasterHelper(getApiCallInterface(provideRetrofit(provideOkHttpClient())))
    }

}
