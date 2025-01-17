package com.easymone.ui.util

import android.content.Context
import android.os.PowerManager

fun isAppAllowedToRunInBackground(context: Context): Boolean {
    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    val packageName = context.packageName
    return powerManager.isIgnoringBatteryOptimizations(packageName)
}