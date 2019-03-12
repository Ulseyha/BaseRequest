package com.pathmazing.baserequest.rest

import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient(private val context: Context,
                 private val baseUrl: String? = "",
                 private val hashMap: HashMap<String, String>? = HashMap(),
                 private val catchSize: Long = 0
                 /*val authenticator: Authenticator? = null*/) {

    data class Builder(private var context: Context,
                       private var baseUrl: String = "",
                       private var hashMap: HashMap<String, String> = HashMap(),
                       private var catchSize: Long  = (1024 * 1024).toLong()
                      /* var authenticator: Authenticator? = null*/) {

        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun header(header: HashMap<String, String>) = apply { this.hashMap = header }
        fun catchSize(catchSize: Long) = apply { this.catchSize = catchSize }
//        fun authenticato(authenticator: Authenticator) = apply { this.authenticator = authenticator }
        fun init() = RestClient(context, baseUrl, hashMap, catchSize/*, authenticator*/)
    }

     inline fun < reified T> build(): T {

        val okHttpClient = OkHttpManager.
                Builder(getContext()).
                catchSize(getCatchSize()).
                header(getHeader()!!).
//                authenticator(authenticator).
                init().
                build()

        val retrofit = Retrofit.Builder()
                .baseUrl(getBaseUrl()!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create<T>(T::class.java)
    }

    fun getContext() : Context{
        return this.context
    }

    fun getCatchSize() : Long{
        return this.catchSize
    }

    fun getBaseUrl() : String? {
        return this.baseUrl
    }

    fun getHeader() : HashMap<String, String>? {
        return this.hashMap
    }

}