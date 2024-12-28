package com.easymone.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.R
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.borderColor
import com.easymone.ui.theme.purple
import com.easymone.ui.theme.white

@Composable
@Preview(showBackground = true)
fun BalanceColumnPreview() {
    BalanceColumn()
}

@Composable
fun BalanceColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(white, shape = RoundedCornerShape(28.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(28.dp))
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
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
                tint = purple
            )
        }
    }
}