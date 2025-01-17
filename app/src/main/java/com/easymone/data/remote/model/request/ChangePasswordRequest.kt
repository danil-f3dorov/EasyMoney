package com.easymone.data.remote.model.request

data class ChangePasswordRequest(
    val token: String,
    val current: String,
    val new: String,
    val confirm: String
)
