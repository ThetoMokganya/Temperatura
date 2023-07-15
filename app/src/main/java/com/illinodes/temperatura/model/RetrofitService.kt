package com.illinodes.temperatura.model

import com.illinodes.temperatura.helper.GlobalConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private var retrofit: Retrofit? = null

    //okHTTPClient for connection settings
    private val okHttpClient = OkHttpClient
        .Builder()
        .readTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(240, TimeUnit.SECONDS)
        .build()

    private val builder = Retrofit
        .Builder()
        .baseUrl(GlobalConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun getService(): Api{
        if (retrofit == null){
            okHttpClient.dispatcher().maxRequestsPerHost = 16
            retrofit = builder.client(okHttpClient).build()
        }

        return retrofit!!.create(Api::class.java)
    }
}