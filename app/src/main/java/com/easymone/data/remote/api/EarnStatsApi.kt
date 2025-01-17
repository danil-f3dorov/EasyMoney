package com.easymone.data.remote.api

import com.easymone.data.remote.model.request.GetStatsRequest
import com.easymone.data.remote.model.response.GetStatsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EarnStatsApi {
    @POST("getStats")
    suspend fun getStats(@Body request: GetStatsRequest): Response<GetStatsResponse>
}