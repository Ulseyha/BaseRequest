package com.pathmazing.baserequest.rest

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

class OkHttpManager(private val context: Context,
                    private val hashMap: HashMap<String, String> = HashMap(),
                    private val catchSize: Long,
                    private var connectionTimeout : Long = 0) {


    data class Builder(private var context: Context,
                       private var hashMap: HashMap<String, String> = HashMap(),
                       private var catchSize: Long = 0,
                       private var connectionTimeout : Long = 0) {

        fun header(header: HashMap<String, String>) = apply { this.hashMap = header }
        fun catchSize(catchSize: Long) = apply { this.catchSize = catchSize }
        fun connectionTimeout(connectionTimeout: Long) = apply { this.connectionTimeout = connectionTimeout }
        fun init() = OkHttpManager(context, hashMap, catchSize)
    }

    fun build(): OkHttpClient {

        val connectionPool = ConnectionPool(100, 18000, TimeUnit.MILLISECONDS)
        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
                .readTimeout(connectionTimeout, TimeUnit.SECONDS)
                .writeTimeout(connectionTimeout, TimeUnit.SECONDS)
                .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(connectionPool)
                .cache(Cache(context.cacheDir, catchSize))
                .addInterceptor(logging)
                .addInterceptor(RewriteRequestInterceptor())
                .addNetworkInterceptor(RewriteResponseCacheControlInterceptor())
                .addInterceptor { chain ->
                    val request = addHeaderParam(hashMap, chain)
                    chain.proceed(request)
                }

        return builder.build()
    }

    private class RewriteRequestInterceptor : Interceptor {

        @Throws(IOException::class)

        override fun intercept(chain: Interceptor.Chain): Response {
            val maxStale = 60 * 60 * 24 * 5
            val request = chain.request().newBuilder().header("Cache-Control", "max-stale=$maxStale").build()
            return chain.proceed(request)
        }
    }

    private class RewriteResponseCacheControlInterceptor : Interceptor {

        @Throws(IOException::class)

        override fun intercept(chain: Interceptor.Chain): Response {
            val maxStale = 60 * 60 * 24 * 5
            val originalResponse = chain.proceed(chain.request())
            return originalResponse.newBuilder().header("Cache-Control", "public, max-age=120, max-stale=$maxStale").build()
        }
    }

    private fun addHeaderParam(hashMap: HashMap<String, String>, chain: Interceptor.Chain): Request {

        val headers = chain.request().newBuilder()

        for (header in hashMap) {
            if (header.value.isNotEmpty())
                headers.addHeader(header.key, header.value)
        }
        return headers.build()
    }
}