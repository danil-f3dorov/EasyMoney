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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.R
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.borderColor
import com.easymone.ui.theme.purple
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.NoRippleInteractionSource
import com.easymone.ui.util.isBackgroundRestricted


@Composable
@Preview(showBackground = true)
fun TrafficSharedColumnPreview() {
    TrafficSharedColumn()
}


@Composable
fun TrafficSharedColumn(
) {
    val context = LocalContext.current
    var switchChecked by remember { mutableStateOf(isBackgroundRestricted(context)) }

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
            text = "0.0000 GB",
            fontSize = 35.sp,
            fontFamily = Roboto.regular,
            color = textColor
        )
        Text(
            text = "Traffic shared in total",
            fontSize = 13.sp,
            fontFamily = Roboto.regular,
            color = blueText
        )
        Spacer(Modifier.height(24.dp))
        TealButtonSample(
            onClick = {}
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.wifi_line),
                    contentDescription = "start earning real $$$ money",
                    tint = white
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Start Sharing",
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
                        switchChecked = !switchChecked
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

        if (switchChecked) {
            BottomSheetBackgroundMode(
                onDismissRequest = { switchChecked = false }
            )
        }


    }


}