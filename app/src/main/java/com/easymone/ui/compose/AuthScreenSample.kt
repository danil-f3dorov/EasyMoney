package com.easymone.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easymone.R
import com.easymone.ui.theme.background
import com.easymone.ui.theme.borderColor
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.NoRippleInteractionSource

@Composable
fun AuthScreenSample(
    popBackStack: (() -> Boolean)? = null,
    boxContent: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(70.dp))
        Image(
            painter = painterResource(R.drawable.easymoney_logo),
            contentDescription = "easy money logo"
        )
        Spacer(Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .background(white, shape = RoundedCornerShape(28.dp))
                .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(28.dp))
        )
        {
            boxContent()
        }
    }

    if (popBackStack != null) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(R.drawable.backarr),
                contentDescription = "back",
                tint = textColor,
                modifier = Modifier
                    .clickable(
                        onClick = { popBackStack() },
                        interactionSource = NoRippleInteractionSource,
                        indication = null
                    )
                    .padding(24.dp)
            )
        }
    }
}