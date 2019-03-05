package com.pathmazing.hr.app

import android.annotation.SuppressLint
import android.app.Application

open class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        application = this@BaseApplication
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var application: Application? = null

        fun getInstance(): Application? {

            if (application == null) {
                synchronized(BaseApplication::class.java) {
                    if (application == null) {
                        application = Application()
                    }
                }
            }
            return application
        }
    }
}