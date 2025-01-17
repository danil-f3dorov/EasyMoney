package com.easymone.ui.screen.login

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easymone.data.remote.model.request.LoginRequest
import com.easymone.ui.compose.AuthColumn
import com.easymone.ui.compose.AuthScreenSample
import com.easymone.ui.compose.AuthTextField
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navSignUp: () -> Unit,
    navHome: () -> Unit,
    popBackStack: () -> Boolean
) {

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    val loginResult = loginViewModel.loginResult.collectAsState()

    LaunchedEffect(loginResult.value) {
        loginResult.value
            .onSuccess { resultCode ->
                when (resultCode) {
                    0 -> navHome()
                }
            }.onFailure {
                println("${it.printStackTrace()}")
            }
    }

    AuthScreenSample(
        popBackStack = popBackStack
    ) {
        AuthColumn(
            title = "Login",
            subtitle = "Don’t have an account? Sing up",
            subtitleOnClick = navSignUp,
            buttonOnClick = {
                loginViewModel.login(emailValue, passwordValue)
            },
            content = {
                AuthTextField(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textValue = emailValue,
                    onTextChange = {
                        emailValue = it
                    },
                    placeholder = "Email"
                )
                AuthTextField(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textValue = passwordValue,
                    onTextChange = {
                        passwordValue = it
                    },
                    placeholder = "Password"
                )
            }

        )

    }
}