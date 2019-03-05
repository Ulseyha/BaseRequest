package com.pathmazing.baserequest.rest

import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    inline fun <reified T> getApiService(context: Context, baseUrl : String,hashMap: HashMap<String, String>): T {

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpManager.getInstance(context,hashMap))
                .build()

        return retrofit.create<T>(T::class.java)
    }
}