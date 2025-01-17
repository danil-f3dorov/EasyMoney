package com.easymone.data.remote.model.request

data class RefreshCaptchaRequest(
    val id: Long,
    val email: String
)
