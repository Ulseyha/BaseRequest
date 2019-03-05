package com.pathmazing.hr.app

import android.annotation.SuppressLint
import android.app.Application

class HRApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        application = this@HRApplication
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var application: Application? = null

        fun getInstance(): Application? {

            if (application == null) {
                synchronized(HRApplication::class.java) {
                    if (application == null) {
                        application = Application()
                    }
                }
            }
            return application
        }
    }
}