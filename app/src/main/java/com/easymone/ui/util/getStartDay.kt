package com.easymone.ui.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getStartDay(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -7)
    return dateFormat.format(calendar.time)
}