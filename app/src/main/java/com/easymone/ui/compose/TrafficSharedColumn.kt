package com.easymone.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.ui.compose.modalsheet.CenteredModalDialog
import com.easymone.ui.screen.home.EarnStatus
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.background2
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.borderColor
import com.easymone.ui.theme.borderColor2
import com.easymone.ui.theme.purple
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.compose.NoRippleInteractionSource
import com.easymone.ui.util.byteToGigabyte
import com.easymone.ui.util.compose.dashBorder
import com.easymone.ui.util.isAppAllowedToRunInBackground
import com.easymone.ui.util.compose.shimmerEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TrafficSharedColumn(
    traffic: String,
    earnStatus: EarnStatus,
    stopEarn: () -> Unit,
    startEarn: () -> Unit,
    loading: Boolean
) {
    var startEarnFlag by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var switchChecked by remember { mutableStateOf(isAppAllowedToRunInBackground(context)) }
    var showDialog by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(white, shape = RoundedCornerShape(28.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(28.dp))
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Traffic Shared",
            fontSize = 16.sp,
            fontFamily = Roboto.regular,
            color = purple,
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "${byteToGigabyte(traffic)} GB",
            fontSize = 35.sp,
            fontFamily = Roboto.regular,
            color = textColor,
            modifier = Modifier.shimmerEffect(loading)
        )
        Text(
            text = "Traffic shared in total",
            fontSize = 13.sp,
            fontFamily = Roboto.regular,
            color = blueText
        )
        Spacer(Modifier.height(24.dp))

        if (earnStatus == EarnStatus.Connected) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = background2, shape = RoundedCornerShape(16.dp))
                    .dashBorder(
                        color = borderColor2,
                        thickness = 2.dp,
                        dashLength = 5.dp,
                        gapLength = 5.dp
                    ),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sharing Activated",
                    fontSize = 15.sp,
                    fontFamily = Roboto.regular,
                    color = purple,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
        }
        TealButtonSample(
            onClick = {
                if (earnStatus == EarnStatus.Connected) {
                    stopEarn()
                } else {
                    if (!isAppAllowedToRunInBackground(context)) {
                        showDialog = true
                        startEarnFlag = true
                    } else {
                        startEarn()
                    }
                }
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(earnStatus.buttonIconId),
                    contentDescription = "start earning real $$$ money",
                    tint = white
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = earnStatus.buttonText,
                    fontSize = 15.sp,
                    fontFamily = Roboto.regular,
                    color = white
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Toggle to turn internet sharing on and off",
                fontSize = 10.sp,
                fontFamily = Roboto.regular,
                color = blueText
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    modifier = Modifier.scale(0.7f),
                    checked = switchChecked,
                    onCheckedChange = {
                        if (it) showDialog = true
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = purple,
                        checkedBorderColor = purple,
                        checkedTrackColor = white,
                        uncheckedThumbColor = blueText,
                        uncheckedBorderColor = blueText,
                        uncheckedTrackColor = white
                    ),
                    interactionSource = NoRippleInteractionSource,
                )
                Text(
                    text = "Running app in the background",
                    fontSize = 14.sp,
                    fontFamily = Roboto.regular,
                    color = blueText
                )
            }
        }
    }

    CenteredModalDialog(
        showDialog,
        onClick = {
            coroutineScope.launch(Dispatchers.Default) {
                val startTime = System.currentTimeMillis()
                while (System.currentTimeMillis() - startTime < 10_000) {
                    val isAllowed = isAppAllowedToRunInBackground(context)
                    switchChecked = isAllowed
                    if (isAllowed) {
                        if (startEarnFlag) {
                            startEarn()
                            startEarnFlag = false
                        }
                        showDialog = false
                        break
                    }
                    delay(100)
                }
            }
        },
        onDismissRequest = {
            if (isAppAllowedToRunInBackground(context)) {
                switchChecked = true
            }
            showDialog = false
        })
}

