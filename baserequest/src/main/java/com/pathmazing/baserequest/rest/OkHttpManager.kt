package com.pathmazing.baserequest.rest

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

class OkHttpManager(private val context: Context,
                    val hashMap: HashMap<String, String> = HashMap(),
                    val catchSize: Long,
                    var authenticator: Authenticator? = null) {

    data class Builder(var context: Context,
                       var hashMap: HashMap<String, String> = HashMap(),
                       var catchSize: Long = (1024 * 1024).toLong(),
                       var authenticator: Authenticator? = null) {

        fun header(header: HashMap<String, String>) = apply { this.hashMap = header }
        fun catchSize(catchSize: Long) = apply { this.catchSize = catchSize }
        fun authenticator(authenticator: Authenticator?) = apply { this.authenticator = authenticator!! }
        fun init() = OkHttpManager(context, hashMap, catchSize, authenticator)
    }

    fun build(): OkHttpClient {

        val connectionPool = ConnectionPool(100, 18000, TimeUnit.MILLISECONDS)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(connectionPool)
                .cache(Cache(context.cacheDir, catchSize)) // 10 MB
                .addInterceptor(logging)
                .addInterceptor(RewriteRequestInterceptor())
                .addNetworkInterceptor(RewriteResponseCacheControlInterceptor())
                .addInterceptor { chain ->
                    val request = addHeaderParam(hashMap, chain)
                    chain.proceed(request)
                }

        if (authenticator != null) builder.authenticator(authenticator!!)
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