package com.easymone.data.remote.model.request

data class ConfirmSignUpRequest(
    val password: String,
    val id: Long,
    val captcha: Int
)
