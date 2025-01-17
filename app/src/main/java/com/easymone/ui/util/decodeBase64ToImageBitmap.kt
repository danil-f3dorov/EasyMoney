package com.easymone.ui.util

import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayInputStream
import java.io.InputStream

fun decodeBase64ToImageBitmap(base64String: String): ImageBitmap? {
    return try {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        val inputStream: InputStream = ByteArrayInputStream(decodedBytes)
        val image = android.graphics.BitmapFactory.decodeStream(inputStream)
        image.asImageBitmap()
    } catch (e: Exception) {
        null
    }
}