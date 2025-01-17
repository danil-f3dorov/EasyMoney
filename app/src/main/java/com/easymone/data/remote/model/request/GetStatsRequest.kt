package com.easymone.data.remote.model.request

data class GetStatsRequest(
    val token: String,
    val start: String,
    val end: String
)
