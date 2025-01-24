package com.easymone.ui.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.R
import com.easymone.data.remote.model.response.ClientStats
import com.easymone.data.remote.model.response.DailyStats
import com.easymone.ui.compose.modalsheet.ModalKeepEarning
import com.easymone.ui.screen.home.HomeViewModel
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.borderColor
import com.easymone.ui.theme.divideColor
import com.easymone.ui.theme.purple
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.compose.NoRippleInteractionSource
import com.easymone.ui.util.compose.shimmerEffect
import java.util.Locale

@Composable
fun BalanceColumn(
    clientStats: ClientStats,
    clientDailyStats: List<DailyStats>,
    homeViewModel: HomeViewModel,
    navNoInternet: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (!homeViewModel.isExpanded.value) 90f else 0f,
        animationSpec = tween(150), label = ""
    )
    var keepEarning by remember { mutableStateOf(false) }

    if(keepEarning) {
        ModalKeepEarning {
           keepEarning = !keepEarning
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(white, shape = RoundedCornerShape(28.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(28.dp))
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Balance",
                fontSize = 16.sp,
                fontFamily = Roboto.regular,
                color = purple
            )
            Icon(
                painter = painterResource(R.drawable.refresh_line),
                contentDescription = "refresh balance",
                tint = purple,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = NoRippleInteractionSource,
                    onClick = { homeViewModel.getStats(navNoInternet) }
                )
            )
        }
        Text(
            text = "$${String.format(Locale.ENGLISH, "%.3f", clientStats.balance.toFloat())}",
            fontFamily = Roboto.regular,
            fontSize = 35.sp,
            color = textColor,
            modifier = Modifier.shimmerEffect(homeViewModel.loading.value)
        )
        Spacer(Modifier.height(8.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = divideColor,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Current earning",
                fontFamily = Roboto.regular,
                fontSize = 14.sp,
                color = textColor
            )
            IconButton(
                onClick = { homeViewModel.isExpanded.value = !homeViewModel.isExpanded.value },
                interactionSource = NoRippleInteractionSource
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.rotate(rotation)
                )
            }
        }
        if (homeViewModel.isExpanded.value) {
            clientDailyStats.forEach {
                DailyStat(it)
            }
        }
        Spacer(Modifier.height(24.dp))
        TealButtonSample(
            onClick = {
                keepEarning = true
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.exchange_dollar_line),
                    contentDescription = null,
                    tint = Color.White
                )
                Text(
                    text = "Request Payout",
                    fontFamily = Roboto.regular,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "The minimum payout amount is $15",
                fontFamily = Roboto.regular,
                fontSize = 10.sp,
                color = blueText

            )
        }
        Spacer(Modifier.height(24.dp))
    }
}
