package com.easymone.data.remote.api

import com.easymone.data.remote.model.request.ChangePasswordRequest
import com.easymone.data.remote.model.request.ConfirmSignUpRequest
import com.easymone.data.remote.model.request.EmailRequest
import com.easymone.data.remote.model.request.GoogleTokenRequest
import com.easymone.data.remote.model.request.LoginRequest
import com.easymone.data.remote.model.request.LogoutRequest
import com.easymone.data.remote.model.request.RefreshCaptchaRequest
import com.easymone.data.remote.model.response.GoogleAuthResponse
import com.easymone.data.remote.model.response.InfoResponse
import com.easymone.data.remote.model.response.LoginResponse
import com.easymone.data.remote.model.response.RefreshCaptchaResponse
import com.easymone.data.remote.model.response.SignUpResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("googleAuth")
    suspend fun signUpGoogleAuth(@Body request: GoogleTokenRequest): Response<GoogleAuthResponse>

    @POST("register")
    suspend fun signUp(@Body request: EmailRequest): Response<SignUpResponse>

    @POST("register")
    suspend fun confirmSignUp(@Body request: ConfirmSignUpRequest): Response<InfoResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("logout")
    suspend fun logout(@Body request: LogoutRequest): Response<InfoResponse>

    @POST("changePass")
    suspend fun changePass(@Body request: ChangePasswordRequest): Response<InfoResponse>

    @POST("forgotPass")
    suspend fun forgotPass(@Body request: EmailRequest): Response<InfoResponse>

    @POST("refreshCaptcha")
    suspend fun refreshCaptcha(@Body request: RefreshCaptchaRequest): Response<RefreshCaptchaResponse>
}