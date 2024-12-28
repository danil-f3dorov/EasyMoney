package com.easymone.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easymone.ui.theme.Roboto
import com.easymone.ui.theme.Urbanist
import com.easymone.ui.theme.blueText
import com.easymone.ui.theme.gray1
import com.easymone.ui.theme.textColor
import com.easymone.ui.theme.white
import com.easymone.ui.util.NoRippleInteractionSource

@Composable
fun AuthColumn(
    title: String,
    subtitle: String,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    emailError: String? = null,
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
    passwordError: String? = null,
    subtitleOnClick: () -> Unit,
    buttonOnClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = title,
            fontFamily = Roboto.regular,
            fontSize = 35.sp,
            color = textColor
        )
        Text(
            text = subtitle,
            fontFamily = Urbanist.regular,
            fontSize = 15.sp,
            color = blueText,
            modifier = Modifier.clickable(
                onClick = subtitleOnClick,
                interactionSource = NoRippleInteractionSource,
                indication = null
            )
        )
        AuthTextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            textValue = emailValue,
            onTextChange = onEmailChange,
            placeholder = "Email"
        )
        if (emailError != null) {
            Text(
                text = emailError
            )
        }
        AuthTextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            textValue = passwordValue,
            onTextChange = onPasswordChange,
            placeholder = "Password"
        )
        if (passwordError != null) {
            Text(
                text = passwordError
            )
        }
        TealButtonSample(
            onClick = buttonOnClick,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                color = white,
                fontSize = 15.sp,
                fontFamily = Roboto.regular
            )
        }
    }
}