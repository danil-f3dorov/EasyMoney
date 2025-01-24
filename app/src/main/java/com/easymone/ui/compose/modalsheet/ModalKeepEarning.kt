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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
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
fun ModalKeepEarning(
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = white,
        tonalElevation = 20.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_keep_earning),
                contentDescription = "image allow earning in background mode"
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = "Keep earning!\nYou can do it!",
                fontSize = 16.sp,
                fontFamily = Roboto.medium,
                color = textColor,
                lineHeight = 20.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "The minimum payout is \$15, so don't \n" +
                        "stop sharing your internet\n" +
                        "You are almost there!",
                fontSize = 12.sp,
                fontFamily = Roboto.regular,
                color = blueText,
                textAlign = TextAlign.Center,
                lineHeight = 15.sp
            )
            Spacer(Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .background(color = white, shape = RoundedCornerShape(16.dp))
                    .border(width = 1.dp, color = teal, shape = RoundedCornerShape(16.dp))
                    .clickable(
                        onClick = {
                            onDismissRequest()
                        },
                        interactionSource = NoRippleInteractionSource,
                        indication = null
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Keep Earning",
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














