package com.pathmazing.hr.rest

import android.support.v7.app.AppCompatActivity
import java.io.IOException
import java.net.Authenticator

//class AuthenticationToken : Authenticator {
//
//    override fun authenticate(route: Route?, response: Response?): Request? {
//
//       return response?.request()!!.newBuilder()
//                    .header("auth", "")
//                    .build()
//
//    }

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
//}