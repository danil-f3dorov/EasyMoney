package com.easymone.ui.screen.start

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest.Builder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymone.data.remote.api.AuthApi
import com.easymone.data.remote.model.request.GoogleTokenRequest
import com.easymone.ui.util.isInternetAvailable
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.text.Typography.dagger

@HiltViewModel
class StartViewModel @Inject constructor(
    private val authApi: AuthApi
) : ViewModel() {

    private val _signInWithGoogleResult = MutableStateFlow(Result.success(-1L))
    val signInWithGoogleResult get() = _signInWithGoogleResult
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying get() = _isPlaying
    val isError = mutableStateOf(false)

    fun signInWithGoogleAccount(context: Context) {
        viewModelScope.launch {
            try {
                isPlaying.value = true
                isError.value = false
                if(!isInternetAvailable()) throw UnknownHostException("no internet")
                val options = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("463914353947-8behfqvuf8fn4ik5190hubcb3us16h7c.apps.googleusercontent.com")
                    .setAutoSelectEnabled(true)
                    .build()

                val request =
                    Builder().addCredentialOption(options).build()
                isPlaying.value = false
                val credential = CredentialManager.create(context)
                    .getCredential(context, request)

                if (credential.credential is CustomCredential && credential.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleCredential =
                        GoogleIdTokenCredential.createFrom(credential.credential.data)
                    _isPlaying.value = true
                    val response =
                        authApi.signUpGoogleAuth(GoogleTokenRequest(googleCredential.idToken))
                            .body()
                    val responseCode = response?.result ?: -1

                    _signInWithGoogleResult.value = Result.success(responseCode)
                }
            } catch (uhe: UnknownHostException) {
                _signInWithGoogleResult.value = Result.failure(uhe)
            } catch (e: Exception) {

            } finally {
                _isPlaying.value = false
            }
        }
    }
    fun resetResult() {
        _signInWithGoogleResult.value = Result.success(-1)
    }
}