package com.easymone.ui.compose.modalsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.R
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.Urbanist
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.orange
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalWarning(
    onDismissRequest: () -> Unit,
    title: String,
    description: String
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = white,
        tonalElevation = 20.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_warning),
                contentDescription = "warning",
                tint = orange
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = Roboto.medium,
                color = textColor
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 12.sp,
                fontFamily = Urbanist.regular,
                color = blueText,
            )
        }
    }
}