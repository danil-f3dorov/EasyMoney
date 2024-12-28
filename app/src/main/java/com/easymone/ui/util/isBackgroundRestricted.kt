package com.easymone.ui.util

import android.app.ActivityManager
import android.content.Context
import android.os.Build

fun isBackgroundRestricted(context: Context): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.isBackgroundRestricted
    }
    return false
}