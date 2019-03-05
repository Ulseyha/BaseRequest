package com.pathmazing.baserequest.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import android.util.Log

class InternetBroadcastReceiver : BroadcastReceiver() {
    private var onInternetConnectionChangeListener: OnInternetConnectionChangeListener? = null

    fun setOnInternetConnectionChangeListener(onInternetConnectionChangeListener: OnInternetConnectionChangeListener) {
        this.onInternetConnectionChangeListener = onInternetConnectionChangeListener
    }

    override fun onReceive(context: Context, intent: Intent) {
        val status = getConnectivityStatusString(context)
        Log.i("CONNECTION_CHANGE", "STATUS" + status!!)
        if (status == NOT_CONNECT) {
            Log.i("CONNECTION_CHANGE ", "INTERNET DISCONNECT")
            //  check !isConnected , cuz don't want to call back redundant
            if (onInternetConnectionChangeListener != null) {
                onInternetConnectionChangeListener!!.onDisconnected()
            }
        } else {
            Log.i("CONNECTION_CHANGE ", "INTERNET CONNECTED")
            //  check !isConnected , cuz don't want to call back redundant
            if (onInternetConnectionChangeListener != null) {
                onInternetConnectionChangeListener!!.onConnected()
            }
        }
    }

    interface OnInternetConnectionChangeListener {
        fun onDisconnected()

        fun onConnected()
    }

    companion object {

        private const val TYPE_WIFI = 1
        private const val TYPE_MOBILE = 2
        private const val TYPE_NOT_CONNECTED = 0
        private const val CONNECT_TO_WIFI = "WIFI"
        private const val CONNECT_TO_MOBILE = "MOBILE"
        private const val NOT_CONNECT = "NOT_CONNECT"
        const val CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"

        fun getConnectivityStatus(context: Context): Int {

            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo

            if (null != activeNetwork) {

                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
                    return TYPE_WIFI

                if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
                    return TYPE_MOBILE
            }
            return TYPE_NOT_CONNECTED
        }

        fun getConnectivityStatusString(context: Context): String? {

            val conn = getConnectivityStatus(context)

            var status: String? = null
            if (conn == TYPE_WIFI) {
                //status = "Wifi enabled";
                status = CONNECT_TO_WIFI
            } else if (conn == TYPE_MOBILE) {
                //status = "Mobile data enabled";
                println(CONNECT_TO_MOBILE)
                status = getNetworkClass(context)
            } else if (conn == TYPE_NOT_CONNECTED) {
                status = NOT_CONNECT
            }

            return status
        }

        fun getNetworkClass(context: Context): String {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            if (info == null || !info.isConnected)
                return "-" //not connected
            if (info.type == ConnectivityManager.TYPE_WIFI)
                return "WIFI"
            if (info.type == ConnectivityManager.TYPE_MOBILE) {
                val networkType = info.subtype
                when (networkType) {
                    TelephonyManager.NETWORK_TYPE_HSPAP  //api<13 : replace by 15
                    -> return "3G"
                    TelephonyManager.NETWORK_TYPE_LTE    //api<11 : replace by 13
                    -> return "4G"
                    else -> return "UNKNOWN"
                }
            }
            return "UNKNOWN"
        }
    }
}
