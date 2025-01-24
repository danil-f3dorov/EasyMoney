package com.easymone.ui.screen.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymone.data.local.UserPreferences
import com.easymone.data.remote.api.AuthApi
import com.easymone.data.remote.model.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _loginResult = MutableStateFlow(Result.success(-1))
    val loginResult: StateFlow<Result<Int>> get() = _loginResult
    private val _error = MutableStateFlow(-1)
    val error get() = _error
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying get() = _isPlaying

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isPlaying.value = true
                val result = authApi.login(LoginRequest(email, password)).body()
                val resultCode = result?.result ?: -1

                _loginResult.value = Result.success(resultCode)
                _error.value = result?.error ?: -1

                if (resultCode == 0) {
                    result?.token?.let { token ->
                        userPreferences.saveUserData(email, token)
                    }
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            } finally {
                _isPlaying.value = false
            }
        }
    }

    fun resetLoginResult() {
        _loginResult.value = Result.success(-1)
    }
}