package com.easymone.ui.compose.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.gray1
import com.easymone.ui.theme.lightGray1
import com.easymone.ui.theme.red
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.compose.NoRippleInteractionSource

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean,
    errorText: String = ""
) {
    val borderColor = remember(isError) { if (isError) red else lightGray1 }
    val cursorColor = remember(isError) { if(isError) red else textColor}

    OutlinedTextField(
        value = textValue,
        onValueChange = onTextChange,
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            errorTextColor = textColor,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            errorBorderColor = borderColor,
            focusedContainerColor = white,
            unfocusedLabelColor = white,
            errorContainerColor = white,
            cursorColor = cursorColor,
            errorCursorColor = cursorColor
        ),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        interactionSource = NoRippleInteractionSource,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 15.sp,
                fontFamily = Roboto.regular,
                color = gray1
            )
        },
        supportingText = {
            if(isError) {
                Text(
                    text = errorText,
                    fontFamily = Roboto.regular,
                    fontSize = 14.sp,
                    color = red
                )
            }
        }
    )
}