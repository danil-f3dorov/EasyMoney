package com.easymone.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easymone.ui.compose.LoadingAnimation
import com.easymone.ui.compose.AuthColumn
import com.easymone.ui.compose.AuthScreenSample
import com.easymone.ui.compose.textfield.EmailTextField
import com.easymone.ui.compose.textfield.PasswordTextField

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navSignUp: () -> Unit,
    navHome: () -> Unit,
    navNoInternet: () -> Unit,
    popBackStack: () -> Boolean,
    initEmail: String = "",
    initPassword: String = ""
) {
    val context = LocalContext.current

    var emailValue by remember { mutableStateOf(initEmail) }
    var emailError by remember { mutableStateOf(false) }
    var emailErrorText by remember { mutableStateOf("") }

    var passwordValue by remember { mutableStateOf(initPassword) }
    var passwordError by remember { mutableStateOf(false) }
    var passwordErrorText by remember { mutableStateOf("") }

    val loginResult = loginViewModel.loginResult.collectAsState()
    val isPlaying = loginViewModel.isPlaying.collectAsState()

    val error = loginViewModel.error.collectAsState()

    val regex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")


    LaunchedEffect(loginResult.value) {
        loginResult.value
            .onSuccess { resultCode ->
                when (resultCode) {
                    -1 -> {}
                    0 -> navHome()
                    4 -> {
                        when(error.value) {
                            0 -> {
                                emailError = true
                                emailErrorText = "not sign up email"
                            }
                            2 -> {
                                emailError = true
                                emailErrorText = "not confirm email"
                            }
                            3 -> {
                                passwordError = true
                                passwordErrorText = "wrong password"
                            }
                            else -> {
                                Toast.makeText(context, "result: 4 error ${error.value}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                    else -> {
                        Toast.makeText(context, "result: $resultCode", Toast.LENGTH_LONG).show()
                    }

                }
            }.onFailure {
                navNoInternet()
            }
        loginViewModel.resetLoginResult()
    }

    AuthScreenSample(
        popBackStack = popBackStack
    ) {
        AuthColumn(
            title = "Login",
            subtitle = "Don’t have an account? ",
            linkString = "Sign up",
            subtitleOnClick = navSignUp,
            buttonOnClick = {
                if(emailValue == "") {
                    emailError = true
                    emailErrorText = "empty field"
                }
                else if(!emailValue.matches(regex)) {
                    emailError = true
                    emailErrorText = "invalid email"
                }
                else if(passwordValue == "") {
                    passwordError = true
                    passwordErrorText = "empty field"
                }
                else if(passwordValue.length < 6) {
                    passwordError = true
                    passwordErrorText = "too short password"
                }
                else {
                    loginViewModel.login(emailValue, passwordValue)
                }
            },
            content = {
                EmailTextField(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textValue = emailValue,
                    onTextChange = {
                        emailValue = it
                        emailError = false
                    },
                    placeholder = "Email",
                    isError = emailError,
                    errorText = emailErrorText
                )
                PasswordTextField(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textValue = passwordValue,
                    onTextChange = {
                        passwordValue = it
                        passwordError = false
                    },
                    placeholder = "Password",
                    isError = passwordError,
                    errorText = passwordErrorText
                )
            }

        )

    }
    LoadingAnimation(isPlaying.value)

}