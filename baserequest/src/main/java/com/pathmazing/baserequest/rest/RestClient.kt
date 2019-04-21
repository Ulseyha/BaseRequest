package com.pathmazing.baserequest.rest

import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException

class RestClient(private val context: Context? = null,
                 private val baseUrl: String? = "",
                 private val hashMap: HashMap<String, String>? = HashMap(),
                 private val catchSize: Long = 0,
                 private val connectionTimeout: Long = 0) {

    companion object {
        private const val CATCH_SIZE = 1024L
        private const val CONNECTION_TIME_OUT = 60L
    }

    data class Builder(private var context: Context? = null,
                       private var baseUrl: String = "",
                       private var hashMap: HashMap<String, String> = HashMap(),
                       private var catchSize: Long = CATCH_SIZE,
                       private var connectionTimeout: Long = CONNECTION_TIME_OUT) {

        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun header(header: HashMap<String, String>) = apply { this.hashMap = header }
        fun catchSize(catchSize: Long) = apply { this.catchSize = catchSize }
        fun connectionTimeout(connectionTimeout: Long) = apply { this.connectionTimeout = connectionTimeout }
        fun init() = RestClient(context, baseUrl, hashMap, catchSize, connectionTimeout)
    }

    inline fun <reified T> build(): T? {

        try {
            val okHttpClient = OkHttpManager.
                    Builder(getContext()!!).
                    catchSize(getCatchSize()).
                    header(getHeader()!!).
                    connectionTimeout(getConnectionTimeout()!!).
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

        } catch (e: NullPointerException) {
            return null
        }
    }

    fun getContext(): Context? {
        return this.context
    }

    fun getCatchSize(): Long {
        return this.catchSize
    }

    fun getBaseUrl(): String? {
        return this.baseUrl
    }

    fun getConnectionTimeout(): Long? {
        return this.connectionTimeout
    }

    fun getHeader(): HashMap<String, String>? {
        return this.hashMap
    }
}