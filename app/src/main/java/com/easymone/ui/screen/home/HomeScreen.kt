package com.easymone.ui.screen.home

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easymone.ui.compose.BalanceColumn
import com.easymone.ui.compose.TrafficSharedColumn
import com.easymone.ui.compose.modalsheet.ModalWarning
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.textColor


@Composable
fun HomeScreen(
    navNoInternet: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val clientStats = homeViewModel.clientStats.collectAsState()
    val dailyStats = homeViewModel.dailyStats.collectAsState()
    val earnStatus = homeViewModel.earnStatus.collectAsState()
    var isEmulator by remember { mutableStateOf(false) }
    var isVpn by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        homeViewModel.getStats(navNoInternet)
    }
    LaunchedEffect(
        earnStatus.value
    ) {
        when (earnStatus.value) {
            EarnStatus.EmulatorEnabled -> {
                isEmulator = true
            }

            EarnStatus.VpnEnabled -> {
                isVpn = true
            }

            else -> {

            }
        }
    }

    if (isEmulator) {
        ModalWarning(
            onDismissRequest = {
                isEmulator = !isEmulator
                homeViewModel.defaultEarnStatus()
            },
            title = "Emulator Detected",
            description = "This app is not supported on emulators"
        )
    }
    if (isVpn) {
        ModalWarning(
            onDismissRequest = {
                isVpn = !isVpn
                homeViewModel.defaultEarnStatus()
            },
            title = "VPN Connection Detected",
            description = "This app does not support VPN connections"
        )
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
            traffic = clientStats.value.traffic,
            homeViewModel = homeViewModel
        )
        Spacer(Modifier.height(8.dp))
        BalanceColumn(
            clientStats = clientStats.value,
            clientDailyStats = dailyStats.value,
            homeViewModel = homeViewModel,
            navNoInternet = navNoInternet
        )
        Spacer(Modifier.height(24.dp))
    }
}