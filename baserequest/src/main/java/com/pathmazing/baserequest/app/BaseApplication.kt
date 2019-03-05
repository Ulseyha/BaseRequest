package com.pathmazing.hr.app

import android.annotation.SuppressLint
import android.app.Application
import okhttp3.Interceptor
import okhttp3.Request

open class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}