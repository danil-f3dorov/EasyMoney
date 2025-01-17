package com.easymone.ui.screen.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymone.data.local.UserPreferences
import com.easymone.data.remote.api.AuthApi
import com.easymone.data.remote.model.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = authApi.login(LoginRequest(email, password)).body()
                val resultCode = result?.result ?: -1

                _loginResult.value = Result.success(resultCode)

                if (resultCode == 0) {
                    result?.token?.let { token ->
                        userPreferences.saveUserData(email, token)
                    }
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}