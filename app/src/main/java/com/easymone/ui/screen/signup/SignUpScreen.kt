package com.easymone.ui.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easymone.ui.compose.AuthColumn
import com.easymone.ui.compose.AuthScreenSample
import com.easymone.ui.compose.AuthTextField
import com.easymone.ui.compose.modalsheet.ModalConfirmAccount
import com.easymone.ui.util.decodeBase64ToImageBitmap
import kotlin.math.sign

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navLogin: () -> Unit,
    navHome: () -> Unit,
    popBackStack: () -> Boolean
) {
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var captchaValue by remember { mutableStateOf("") }
    var captchaImageBase64 by remember { mutableStateOf("") }
    var isCaptchaMode by remember { mutableStateOf(false) }
    var showModalConfirmAccount by remember { mutableStateOf(false) }
    if (showModalConfirmAccount) {
        ModalConfirmAccount(
            onDismissRequest = { showModalConfirmAccount = false }
        )
    }

    val captcha = signUpViewModel.captchaBase64.collectAsState()
    val signUpResult = signUpViewModel.signUpResult.collectAsState()
    val signUpConfirmResult = signUpViewModel.confirmSignUpResult.collectAsState()

    LaunchedEffect(captcha.value) {
        captchaImageBase64 = signUpViewModel.captchaBase64.value
    }

    LaunchedEffect(signUpResult.value) {
        signUpResult.value.onSuccess { resultCode ->
            if(resultCode == 0) {
                isCaptchaMode = true
            }
        }
    }

    LaunchedEffect(signUpConfirmResult.value) {
        signUpConfirmResult.value.onSuccess { resultCode ->
            if(resultCode == 0) {
                showModalConfirmAccount = true
            }
        }
    }

    AuthScreenSample(
        popBackStack = popBackStack
    ) {
        AuthColumn(
            title = if (!isCaptchaMode) "Sign in" else "Confirm Sign in",
            subtitle = "Already have an account? Log in",
            subtitleOnClick = navLogin,
            buttonOnClick = {
                if (isCaptchaMode) signUpViewModel.confirmSignUp(
                    passwordValue,
                    captchaValue.toIntOrNull() ?: -1
                )
                else signUpViewModel.signUp(emailValue)
            },
            content = {
                if (!isCaptchaMode) {
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
                } else {
                    Image(
                        bitmap = decodeBase64ToImageBitmap(captchaImageBase64)!!,
                        contentDescription = "Captcha Image",
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(100.dp),
                        contentScale = ContentScale.Fit
                    )

                    AuthTextField(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textValue = captchaValue,
                        onTextChange = {
                            captchaValue = it
                        },
                        placeholder = "Captcha"
                    )
                }
            }
        )

    }
}



















