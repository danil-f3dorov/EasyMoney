package com.easymone.ui.util.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dashBorder(
    color: Color = Color.Black,
    thickness: Dp = 2.dp,
    dashLength: Dp = 10.dp,
    gapLength: Dp = 10.dp
): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidth = thickness.toPx()
        val dashLengthPx = dashLength.toPx()
        val gapLengthPx = gapLength.toPx()

        val pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLengthPx, gapLengthPx),
            phase = 0f
        )

        drawRoundRect(
            color = color,
            size = size,
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = strokeWidth,
                pathEffect = pathEffect
            ),
            cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
        )
    }
)