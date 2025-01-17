package com.easymone.ui.util

import java.util.Locale

fun byteToGigabyte(bytesString: String): String {
    val bytes = bytesString.toFloatOrNull() ?: return "-1"
    val bytesInGigabyte = 1024 * 1024 * 1024
    val gigabytes = bytes / bytesInGigabyte
    return String.format(Locale.ENGLISH, "%.4f", gigabytes)
}