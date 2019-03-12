package com.pathmazing.baserequest.rest

import android.content.Context
import okhttp3.Authenticator
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient(val context: Context,
                 val baseUrl: String? = "",
                 val hashMap: HashMap<String, String>? = HashMap(),
                 val catchSize: Long = (1024 * 1024).toLong(),
                 val authenticator: Authenticator? = null) {

//    inline fun <reified T> getApiService(context: Context, baseUrl: String, hashMap: HashMap<String, String>): T {
//
//        val retrofit = Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(OkHttpManager.getInstance(context, hashMap))
//                .build()
//
//        return retrofit.create<T>(T::class.java)
//    }

    data class Builder(var context: Context,
                       var baseUrl: String,
                       var hashMap: HashMap<String, String>,
                       var catchSize: Long,
                       var authenticator: Authenticator) {

        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun header(header: HashMap<String, String>) = apply { this.hashMap = header }
        fun catchSize(catchSize: Long) = apply { this.catchSize = catchSize }
        fun authenticato(authenticator: Authenticator) = apply { this.authenticator = authenticator }
        fun init() = RestClient(context, baseUrl, hashMap, catchSize, authenticator)
    }

    inline fun <reified T> build(): T {

        val okHttpClient = OkHttpManager.
                Builder(context).
                catchSize(catchSize).
                header(hashMap!!).
                authenticator(authenticator).
                init().
                build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create<T>(T::class.java)
    }
}