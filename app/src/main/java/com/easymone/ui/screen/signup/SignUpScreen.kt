package com.easymone.ui.screen.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.easymone.ui.compose.AuthColumn
import com.easymone.ui.compose.AuthScreenSample

@Composable
fun SignUpScreen(
    navLogin: () -> Unit,
    navHome: () -> Unit,
    popBackStack: () -> Boolean
) {

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    AuthScreenSample(
        popBackStack = popBackStack
    ) {
        AuthColumn(
            title = "Sign in",
            subtitle = "Already have an account? Log in",
            emailValue = emailValue,
            onEmailChange = {
                emailValue = it
            },
            passwordValue = passwordValue,
            onPasswordChange = {
                passwordValue = it
            },
            subtitleOnClick = navLogin,
            buttonOnClick = navHome
        )
    }
}



















