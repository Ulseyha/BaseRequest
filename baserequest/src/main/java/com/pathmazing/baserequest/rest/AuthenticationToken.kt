package com.pathmazing.baserequest.rest

import android.support.v7.app.AppCompatActivity
import com.pathmazing.hr.app.BaseApplication
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class AuthenticationToken : Authenticator {

    override fun authenticate(route: Route?, response: Response?): Request? {

       return response?.request()!!.newBuilder()
                    .header("auth", "")
                    .build()

    }

//    @Throws(IOException::class)
//    override fun authenticate(route: Route, response: Response): Request? {
//
//        return if (response.request().header("auth") != null) {
//
//            val activity = BaseApplication.getInstance() as AppCompatActivity
//            activity.runOnUiThread(object : Runnable {
//                override fun run() {
////                                LoginActivity.expireAccessToken((AppCompatActivity) App.getContext());
//                }
//            })
//            null
//        } else {
//            response.request().newBuilder()
//                    .header("auth", "")
//                    .build()
//        }
//    }
}