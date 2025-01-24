package com.easymone.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easymone.ui.theme.teal
import com.easymone.ui.util.compose.NoRippleInteractionSource

@Composable
@Preview(showBackground = true)
fun TealButtonSamplePreview() {
    TealButtonSample(
        onClick = {}
    ) {
        Text("Teal Button")
    }
}

@Composable
fun TealButtonSample(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(teal, shape = RoundedCornerShape(16.dp))
            .clickable(
                onClick = onClick,
                interactionSource = NoRippleInteractionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}