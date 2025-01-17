package com.easymone.data.local

import android.content.Context

class UserPreferences(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserData(email: String, token: String) {
        sharedPreferences.edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_TOKEN, token)
            .apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getString(KEY_TOKEN, null) != null
    }

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_TOKEN = "token"
    }
}