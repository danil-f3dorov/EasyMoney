package com.easymone.ui.screen.nointernet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easymone.ui.util.isInternetAvailable

@Composable
fun NoInternetScreen(popBackStack: () -> Boolean) {

    BackHandler {
        if (isInternetAvailable()) {
            popBackStack()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "No internet connection"
        )
        Button(
            onClick = {
                if(isInternetAvailable()) {
                    popBackStack()
                }
            }
        ) {
            Text(
                text = "try to connect"
            )
        }
    }
}