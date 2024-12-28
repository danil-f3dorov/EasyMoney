package com.easymone.ui.compose

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.easymone.R
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.teal
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.NoRippleInteractionSource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetBackgroundMode(
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
                painter = painterResource(R.drawable.img_background_mode),
                contentDescription = "image allow earning in background mode"
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = "Do you want running app\n" +
                        "in the background?",
                fontSize = 16.sp,
                fontFamily = Roboto.medium,
                color = textColor
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
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            intent.setData(Uri.parse("package:" + context.packageName))
                            context.startActivity(intent)
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
        }

    }
}