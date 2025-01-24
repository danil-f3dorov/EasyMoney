package com.easymone.ui.screen.start

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest.Builder
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.easymone.R
import com.easymone.data.remote.model.request.GoogleTokenRequest
import com.easymone.ui.compose.AuthScreenSample
import com.easymone.ui.compose.LoadingAnimation
import com.easymone.ui.compose.TealButtonSample
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.purple
import com.easymone.ui.theme.red
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException


@Composable
fun StartScreen(
    navHome: () -> Unit,
    navSignUp: () -> Unit,
    navNoInternet: () -> Unit,
    startViewModel: StartViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val signInResult = startViewModel.signInWithGoogleResult.collectAsState()
    val isPlaying = startViewModel.isPlaying.collectAsState()

    LaunchedEffect(signInResult.value) {
        signInResult.value
            .onSuccess { resultCode ->
                when (resultCode) {
                    -1L -> {}
                    0L -> navHome()
                    1L -> startViewModel.isError.value = true
                    else -> {
                        Toast.makeText(context, "result code: $resultCode", Toast.LENGTH_LONG)
                            .show()
                    }
                }

            }.onFailure {
                when (it) {
                    is UnknownHostException -> navNoInternet()
                    else -> {
                        Toast.makeText(context, "error: $it", Toast.LENGTH_LONG).show()
                    }
                }
            }
        startViewModel.resetResult()
    }

    AuthScreenSample(
        popBackStack = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Start your",
                    fontSize = 35.sp,
                    fontFamily = Roboto.regular,
                    color = textColor
                )
                Text(
                    text = "Earning!",
                    fontSize = 35.sp,
                    fontFamily = Roboto.regular,
                    color = purple
                )
            }
            Spacer(Modifier.height(24.dp))
            TealButtonSample(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {
                    navSignUp()
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.mail_line),
                        contentDescription = "sign in with mail",
                        tint = white
                    )
                    Text(
                        text = "Continue with Email",
                        fontFamily = Roboto.regular,
                        fontSize = 15.sp,
                        color = white
                    )
                }
            }
            Text(
                text = "Or",
                fontFamily = Roboto.regular,
                fontSize = 15.sp,
                color = blueText,
                modifier = Modifier.padding(vertical = 24.dp)
            )
            TealButtonSample(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {
                    startViewModel.signInWithGoogleAccount(context)
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.google_line),
                        contentDescription = "sign in with google   ",
                        tint = white
                    )
                    Text(
                        text = "With Google Account",
                        fontFamily = Roboto.regular,
                        fontSize = 15.sp,
                        color = white
                    )
                }
            }
            if (startViewModel.isError.value) {
                Text(
                    text = "Get duplicate email",
                    fontFamily = Roboto.regular,
                    fontSize = 14.sp,
                    color = red,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

        }
    }
    LoadingAnimation(isPlaying.value)
}






















