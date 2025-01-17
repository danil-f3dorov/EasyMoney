package com.easymone.ui.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getToday(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    return dateFormat.format(calendar.time)
}