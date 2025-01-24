package com.easymone.data.remote.model.response

data class LoginResponse(
    val result: Int,
    val error: Int,
    val token: String
)
