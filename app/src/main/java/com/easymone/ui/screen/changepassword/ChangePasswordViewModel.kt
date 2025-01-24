package com.easymone.ui.screen.changepassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymone.data.local.UserPreferences
import com.easymone.data.remote.api.AuthApi
import com.easymone.data.remote.model.request.ChangePasswordRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class ChangePasswordViewModel @Inject constructor(
//    private val authApi: AuthApi,
//    private val userPreferences: UserPreferences
//): ViewModel() {
//
//    private var _changePasswordResult = MutableStateFlow(Result.success(-1))
//    val changePasswordResult get() = _changePasswordResult
//
//    fun changePassword(
//        currentPassword: String,
//        newPassword: String,
//        confirmPassword: String
//    ) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                authApi.changePass(ChangePasswordRequest(
//
//                ))
//            }
//        }
//    }
//}