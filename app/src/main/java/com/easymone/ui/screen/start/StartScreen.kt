package com.easymone.ui.screen.start

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest.Builder
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import com.easymone.R
import com.easymone.data.remote.GoogleTokenWrapper
import com.easymone.data.remote.authApi
import com.easymone.ui.compose.AuthScreenSample
import com.easymone.ui.compose.TealButtonSample
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.purple
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun StartScreen(
    navHome: () -> Unit,
    navSignUp: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    AuthScreenSample(
        popBackStack = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
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
                color = blueText
            )
            TealButtonSample(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {

                    val options = GetGoogleIdOption.Builder()
                        .setFilterByAuthorizedAccounts(false)
                        .setServerClientId("463914353947-8behfqvuf8fn4ik5190hubcb3us16h7c.apps.googleusercontent.com")
                        .setAutoSelectEnabled(false)
                        .build()

                    val request =
                        Builder().addCredentialOption(options).build()
                    coroutineScope.launch(Dispatchers.Default) {
                        val response = CredentialManager.create(context)
                            .getCredential(context, request)

                        if (response.credential is CustomCredential && response.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                            val googleCredential =
                                GoogleIdTokenCredential.createFrom(response.credential.data)
                            authApi.sendGoogleToken(GoogleTokenWrapper(googleCredential.idToken))
                            withContext(Dispatchers.Main) {
                                navHome()
                            }
                            Log.d(
                                "OAuth",
                                "onCreate: Signed In as: ${googleCredential.id} ${googleCredential.idToken}"
                            )
                        }
                    }
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

        }
    }
}






















