package com.easymone.ui.screen.nointernet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.R
import com.easymone.ui.compose.AuthScreenSample
import com.easymone.ui.compose.TealButtonSample
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.Urbanist
import com.easymone.ui.theme.purple
import com.easymone.ui.theme.textColor
import com.easymone.ui.util.isInternetAvailable

@Composable
fun NoInternetScreen(navigate: () -> Unit) {

    var hasNav by remember { mutableStateOf(false) }

    fun navigateWrapper() {
        if (isInternetAvailable() && !hasNav) {
            hasNav = true
            navigate()
        }
    }

    BackHandler {
        navigateWrapper()
    }

    AuthScreenSample {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(Modifier)
            Icon(
                painter = painterResource(R.drawable.ic_nowifi_big),
                contentDescription = "no wifi available",
                tint = purple
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontFamily = Roboto.regular, fontSize = 35.sp, color = purple)) {
                        append("Oops \n")
                    }

                    withStyle(style = SpanStyle(fontFamily = Urbanist.regular, fontSize = 18.sp, color = textColor)) {
                        append("No internet connection")
                    }
                },
                textAlign = TextAlign.Center,
            )

            TealButtonSample(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                onClick = { navigateWrapper() }
            ) {
                Text(
                    text = "Refresh",
                    fontFamily = Roboto.regular,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            Spacer(Modifier)

        }
    }
}