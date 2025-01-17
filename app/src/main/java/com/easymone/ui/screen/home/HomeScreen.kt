package com.easymone.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easymone.ui.compose.BalanceColumn
import com.easymone.ui.compose.TrafficSharedColumn
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.textColor


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val clientStats = homeViewModel.clientStats.collectAsState()
    val dailyStats = homeViewModel.dailyStats.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getStats()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home",
            fontSize = 16.sp,
            fontFamily = Roboto.regular,
            color = textColor
        )
        Spacer(Modifier.height(18.dp))
        TrafficSharedColumn(
            traffic = clientStats.value.traffic
        )
        Spacer(Modifier.height(8.dp))
        BalanceColumn(
            onClickRefreshStats = {},
            clientStats = clientStats.value,
            clientDailyStats = dailyStats.value
        )
        Spacer(Modifier.height(24.dp))
    }
}