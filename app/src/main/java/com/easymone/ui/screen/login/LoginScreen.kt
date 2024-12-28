package com.easymone.ui.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.easymone.ui.compose.AuthColumn
import com.easymone.ui.compose.AuthScreenSample

@Composable
fun LoginScreen(
    navSignUp: () -> Unit,
    navHome: () -> Unit,
    popBackStack: () -> Boolean
) {

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    AuthScreenSample(
        popBackStack = popBackStack
    ) {
        AuthColumn(
            title = "Login",
            subtitle = "Don’t have an account? Sing up",
            emailValue = emailValue,
            onEmailChange = {
                emailValue = it
            },
            passwordValue = passwordValue,
            onPasswordChange = {
                passwordValue = it
            },
            subtitleOnClick = navSignUp,
            buttonOnClick = navHome
        )
    }
}