package com.easymone.ui.compose.modalsheet

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.easymone.R
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.teal
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.compose.NoRippleInteractionSource


@Composable
fun CenteredModalDialog(
    isVisible: Boolean,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    val context = LocalContext.current

    if (isVisible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(onClick = onDismissRequest)
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = white, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                        .clickable(enabled = false,
                            interactionSource = NoRippleInteractionSource,
                            indication = null,
                            onClick = {}),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_background_mode),
                            contentDescription = "image allow earning in background mode",
                        )
                        Spacer(Modifier.height(32.dp))
                        Text(
                            text = "Do you want running app\nin the background?",
                            fontSize = 16.sp,
                            fontFamily = Roboto.medium,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Confirm action in settings",
                            fontSize = 12.sp,
                            fontFamily = Roboto.regular,
                            color = blueText
                        )
                        Spacer(Modifier.height(32.dp))
                        Box(
                            modifier = Modifier
                                .background(color = white, shape = RoundedCornerShape(16.dp))
                                .border(width = 1.dp, color = teal, shape = RoundedCornerShape(16.dp))
                                .clickable(
                                    onClick = {
                                        val intent =
                                            Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                                        intent.data = Uri.parse("package:${context.packageName}")
                                        context.startActivity(intent)
                                        onClick()
                                    },
                                    interactionSource = NoRippleInteractionSource,
                                    indication = null
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "To Settings",
                                fontSize = 15.sp,
                                fontFamily = Roboto.regular,
                                color = teal,
                                modifier = Modifier
                                    .padding(horizontal = 31.dp)
                                    .padding(vertical = 12.dp)
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}