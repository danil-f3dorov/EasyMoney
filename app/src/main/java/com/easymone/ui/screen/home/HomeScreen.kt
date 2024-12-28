package com.easymone.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.ui.compose.TrafficSharedColumn
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.textColor

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 24.dp).padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home",
            fontSize = 16.sp,
            fontFamily = Roboto.regular,
            color = textColor
        )
        Spacer(Modifier.height(18.dp))
        TrafficSharedColumn()
    }
}