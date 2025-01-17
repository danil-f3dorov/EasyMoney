package com.easymone.data.remote.model.response

data class SignUpResponse(
    val result: Int,
    val captcha: String,
    val id: Long,
    val info: String? = null
)