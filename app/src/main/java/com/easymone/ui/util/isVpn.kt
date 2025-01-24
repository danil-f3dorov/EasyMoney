package com.easymone.ui.util

import android.content.Context
import android.net.ConnectivityManager


fun isVpn(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.getNetworkInfo(ConnectivityManager.TYPE_VPN)!!.isConnectedOrConnecting
}