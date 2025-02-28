package com.easymone.ui.screen.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easymone.R
import com.easymone.ui.compose.AuthColumn
import com.easymone.ui.compose.AuthScreenSample
import com.easymone.ui.compose.LoadingAnimation
import com.easymone.ui.compose.textfield.EmailTextField
import com.easymone.ui.compose.modalsheet.ModalConfirmAccount
import com.easymone.ui.compose.textfield.CaptchaTextField
import com.easymone.ui.compose.textfield.PasswordTextField
import com.easymone.ui.theme.purple
import com.easymone.ui.util.compose.NoRippleInteractionSource
import com.easymone.ui.util.decodeBase64ToImageBitmap

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navLogin: () -> Unit,
    navNoInternet: () -> Unit,
    popBackStack: () -> Boolean,
    navLoginWithArgs: (String, String) -> Unit
) {
    val context = LocalContext.current
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var captchaValue by remember { mutableStateOf("") }
    var captchaImageBase64 by remember { mutableStateOf("") }
    var isCaptchaMode by remember { mutableStateOf(false) }
    var showModalConfirmAccount by remember { mutableStateOf(false) }
    if (showModalConfirmAccount) {
        ModalConfirmAccount(
            navToLogin = { navLoginWithArgs(emailValue, passwordValue) },
            onDismissRequest = { showModalConfirmAccount = false }
        )
    }

    val captcha = signUpViewModel.captchaBase64.collectAsState()
    val signUpResult = signUpViewModel.signUpResult.collectAsState()
    val signUpConfirmResult = signUpViewModel.confirmSignUpResult.collectAsState()
    val isPlaying = signUpViewModel.isPlaying.collectAsState()

    var emailError by remember { mutableStateOf(false) }
    var emailErrorText by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }
    var passwordErrorText by remember { mutableStateOf("") }
    var captchaError by remember { mutableStateOf(false) }
    var captchaErrorText by remember { mutableStateOf("") }

    val regex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

    LaunchedEffect(captcha.value) {
        captchaImageBase64 = signUpViewModel.captchaBase64.value
    }

    LaunchedEffect(signUpResult.value) {
        signUpResult.value.fold(
            onSuccess = { resultCode ->
                when (resultCode) {
                    0 -> isCaptchaMode = true
                    2 -> {
                        emailError = true
                        emailErrorText = "duplicate email"
                    }
                }
            },
            onFailure = {
                navNoInternet()
            }
        )
        signUpViewModel.resetSignUpResult()
    }

    LaunchedEffect(signUpConfirmResult.value) {
        signUpConfirmResult.value
            .onSuccess { resultCode ->
                when (resultCode) {
                    -1 -> {}
                    0 -> showModalConfirmAccount = true
                    4 -> {
                        captchaError = true
                        captchaErrorText = "wrong captcha"
                    }

                    else -> {
                        Toast.makeText(context, "error: $resultCode", Toast.LENGTH_LONG).show()
                    }
                }

            }
            .onFailure {
                navNoInternet()
            }
        signUpViewModel.resetConfirmSignUpResult()
    }

    AuthScreenSample(
        popBackStack = popBackStack
    ) {
        AuthColumn(
            title = if (!isCaptchaMode) "Sign up" else "Confirm Sign up",
            subtitle = "Already have an account? ",
            linkString = "Log in",
            subtitleOnClick = navLogin,
            buttonOnClick = {
                if (isCaptchaMode) signUpViewModel.confirmSignUp(
                    passwordValue,
                    captchaValue.toIntOrNull() ?: -1
                )
                else {
                    if (emailValue == "") {
                        emailError = true
                        emailErrorText = "empty field"
                    } else if (!emailValue.matches(regex)) {
                        emailError = true
                        emailErrorText = "invalid email"
                    } else if (passwordValue == "") {
                        passwordError = true
                        passwordErrorText = "empty field"
                    } else if (passwordValue.length < 6) {
                        passwordError = true
                        passwordErrorText = "too short password"
                    } else {
                        signUpViewModel.signUp(emailValue)
                    }
                }
            },
            content = {
                if (!isCaptchaMode) {
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
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            bitmap = decodeBase64ToImageBitmap(captchaImageBase64)!!,
                            contentDescription = "Captcha Image",
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(75.dp),
                            contentScale = ContentScale.Fit
                        )

                        Icon(
                            painterResource(R.drawable.refresh_line),
                            contentDescription = "refresh captcha",
                            tint = purple,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = NoRippleInteractionSource,
                                    onClick = { signUpViewModel.refreshCaptcha(emailValue) }
                                )
                        )
                    }
                    CaptchaTextField(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textValue = captchaValue,
                        onTextChange = {
                            captchaValue = it
                            captchaError = false
                        },
                        placeholder = "Captcha",
                        isError = captchaError,
                        errorText = captchaErrorText
                    )
                }
            }
        )

    }
    LoadingAnimation(isPlaying.value)

}



















