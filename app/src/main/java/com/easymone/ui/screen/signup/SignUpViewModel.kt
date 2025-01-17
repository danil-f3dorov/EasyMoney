package com.easymone.ui.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymone.data.remote.api.AuthApi
import com.easymone.data.remote.model.request.ConfirmSignUpRequest
import com.easymone.data.remote.model.request.EmailRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authApi: AuthApi
) : ViewModel() {

    private val _signUpResult = MutableStateFlow(Result.success(-1))
    val signUpResult get() = _signUpResult

    private val _confirmSignUpResult = MutableStateFlow(Result.success(-1))
    val confirmSignUpResult get() = _confirmSignUpResult

    private val _tempId = MutableStateFlow(-1L)
    private val _captchaBase64 = MutableStateFlow("")
    val captchaBase64 get() = _captchaBase64


    fun signUp(email: String) {
        viewModelScope.launch {
            try {
                val result = authApi.signUp(EmailRequest(email)).body()
                val resultCode = result?.result ?: -1
                _tempId.value = result?.id ?: -1
                _captchaBase64.value = result?.captcha ?: ""

                _signUpResult.value = Result.success(resultCode)
            } catch (e: Exception) {
                _signUpResult.value = Result.failure(e)
            }
        }
    }

    fun confirmSignUp(password: String, captcha: Int) {
        viewModelScope.launch {
            try {
                val result = authApi.confirmSignUp(ConfirmSignUpRequest(password, _tempId.value, captcha)).body()
                val resultCode = result?.result ?: -1
                val errorCode = result?.error ?: -1

                if(errorCode == 0) {
                    _confirmSignUpResult.value = Result.success(resultCode)
                }

            } catch (e: Exception) {
                _confirmSignUpResult.value = Result.failure(e)
            }
        }
    }
}















