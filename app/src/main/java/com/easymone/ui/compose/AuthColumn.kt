package com.easymone.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.Urbanist
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.purple
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.compose.NoRippleInteractionSource

@Composable
fun AuthColumn(
    title: String,
    subtitle: String,
    linkString: String,
    content: @Composable () -> Unit,
    subtitleOnClick: () -> Unit,
    buttonOnClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = title,
            fontFamily = Roboto.regular,
            fontSize = 35.sp,
            color = textColor
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontFamily = Urbanist.regular, fontSize = 15.sp, color = blueText)) {
                    append(subtitle)
                }
                withStyle(style = SpanStyle(fontFamily = Urbanist.semiBold, fontSize = 15.sp, color = purple)) {
                    append(linkString)
                }
            },
            fontFamily = Urbanist.regular,
            fontSize = 15.sp,
            color = blueText,
            modifier = Modifier.clickable(
                onClick = subtitleOnClick,
                interactionSource = NoRippleInteractionSource,
                indication = null
            )
        )
        content()
        TealButtonSample(
            onClick = buttonOnClick,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                color = white,
                fontSize = 15.sp,
                fontFamily = Roboto.regular
            )
        }
    }
}