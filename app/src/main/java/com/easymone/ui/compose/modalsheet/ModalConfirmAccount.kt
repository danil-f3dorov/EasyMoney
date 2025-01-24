package com.easymone.ui.compose.modalsheet

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.R
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.teal
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.compose.NoRippleInteractionSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalConfirmAccount(
    navToLogin: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = white,
        tonalElevation = 20.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_confirm_mail),
                contentDescription = "image allow earning in background mode"
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = "Complete Registration",
                fontSize = 16.sp,
                fontFamily = Roboto.medium,
                color = textColor
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "To log into your account, \n" +
                        "confirm it by email.",
                fontSize = 12.sp,
                fontFamily = Roboto.regular,
                color = blueText,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(color = white, shape = RoundedCornerShape(16.dp))
                        .border(width = 1.dp, color = blueText, shape = RoundedCornerShape(16.dp))
                        .clickable(
                            onClick = {
                                val emailIntent = Intent(Intent.ACTION_MAIN).apply {
                                    addCategory(Intent.CATEGORY_APP_EMAIL)
                                }

                                try {
                                    context.startActivity(emailIntent)
                                } catch (e: ActivityNotFoundException) {
                                    Toast.makeText(context, "no available mail app", Toast.LENGTH_SHORT).show()
                                }
                            },
                            interactionSource = NoRippleInteractionSource,
                            indication = null
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Email",
                        fontSize = 15.sp,
                        fontFamily = Roboto.regular,
                        color = blueText,
                        modifier = Modifier
                            .padding(horizontal = 31.dp)
                            .padding(vertical = 12.dp)
                    )
                }
                Spacer(Modifier.width(32.dp))
                Box(
                    modifier = Modifier
                        .background(color = white, shape = RoundedCornerShape(16.dp))
                        .border(width = 1.dp, color = teal, shape = RoundedCornerShape(16.dp))
                        .clickable(
                            onClick = {
                                navToLogin()
                                onDismissRequest()
                            },
                            interactionSource = NoRippleInteractionSource,
                            indication = null
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Login",
                        fontSize = 15.sp,
                        fontFamily = Roboto.regular,
                        color = teal,
                        modifier = Modifier
                            .padding(horizontal = 31.dp)
                            .padding(vertical = 12.dp)
                    )
                }
            }

        }

    }
}