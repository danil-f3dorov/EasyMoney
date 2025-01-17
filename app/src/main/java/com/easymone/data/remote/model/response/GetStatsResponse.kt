package com.easymone.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class GetStatsResponse(
    @SerializedName("c") val clientStats: ClientStats,
    @SerializedName("d")val dailyStats: List<DailyStats>,
    val result: Int
)

data class ClientStats(
    @SerializedName("b") val balance: String,
    @SerializedName("t") val traffic: String
)

data class DailyStats(
    @SerializedName("a")val data: String,
    @SerializedName("e")val earn: String,
    @SerializedName("t")val t: String
)