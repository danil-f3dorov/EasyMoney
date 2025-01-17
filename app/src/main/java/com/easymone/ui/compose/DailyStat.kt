package com.easymone.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.data.remote.model.response.DailyStats
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.divideColor
import com.easymone.ui.theme.teal

@Composable
fun DailyStat(dailyStats: DailyStats) {

    HorizontalDivider(
        thickness = 1.dp,
        color = divideColor,
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dailyStats.data,
            fontFamily = Roboto.light,
            fontSize = 14.sp,
            color = blueText
        )
        Text(
            text = '$' + dailyStats.earn,
            fontFamily = Roboto.medium,
            fontSize = 14.sp,
            color = teal
        )
    }
}