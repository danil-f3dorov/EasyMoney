package com.easymone.data.remote

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


data class GoogleTokenWrapper(val token: String)

interface AuthApi {
    @POST("googleCallback")
    suspend fun sendGoogleToken(@Body googleToken: GoogleTokenWrapper): Response<ResponseBody>
}

val loginInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loginInterceptor)
    .build()

val retrofitClient = Retrofit.Builder()
    .baseUrl("https://earn-api.ipht.net/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

val authApi = retrofitClient.create(AuthApi::class.java)
