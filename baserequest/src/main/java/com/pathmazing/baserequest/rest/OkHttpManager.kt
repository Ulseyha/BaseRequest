package com.pathmazing.baserequest.rest

import com.pathmazing.hr.app.BaseApplication
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

object OkHttpManager{

    private var sInstance: OkHttpClient? = null

    val instance: OkHttpClient get() {

        if (sInstance == null) {

                val connectionPool = ConnectionPool(100, 18000, TimeUnit.MILLISECONDS)
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                sInstance = OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .connectionPool(connectionPool)
                        .cache(Cache(BaseApplication.getInstance()?.baseContext!!.cacheDir, (1024 * 1024).toLong())) // 10 MB
                        .addInterceptor(logging)
                        .addInterceptor(OkHttpManager.RewriteRequestInterceptor())
                        .addNetworkInterceptor(OkHttpManager.RewriteResponseCacheControlInterceptor())
                        .addInterceptor { chain ->
                            val request = addHeaderParam(chain)
                            chain.proceed(request)
                        }
                        /*.authenticator(AuthenticationToken())*/
                        .build()
            }
            return this.sInstance!!
        }

//    val accessKey: String?
//        get() {
//            val context = BaseApplication.applicationContext
//            val loginRespond = UserSharePreference.getInstance(context).getUserLogin(context)
//            if (loginRespond != null) {
//                Log.d("AccessKey", "getAccessKey: " + loginRespond.accessToken!!)
//                return loginRespond.accessToken
//            }
//            return Constants.USER_ACCESS_TOKEN_TEST
//        }

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

    private fun addHeaderParam(chain: Interceptor.Chain): Request {

        val request: Request
        var accessToken = ""

        try {
            accessToken = "Bearer " + "preferences.getAccessToken()"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } finally {
            request = chain.request().newBuilder()
                    .addHeader("Access-Token", accessToken)
                    .build()
        }
        return request
    }

}