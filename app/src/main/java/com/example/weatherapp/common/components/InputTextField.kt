package com.example.weatherapp.common.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    hint: String,
    isSensitive: Boolean = false,
    onInputChange: (input: String) -> Unit,
) {
    var inputText by remember {
        mutableStateOf("")
    }

    TextField(
        modifier = modifier,
        value = inputText,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.White
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isSensitive) {
                KeyboardType.Password
            } else {
                KeyboardType.Text
            }
        ),
        visualTransformation = if (isSensitive) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        onValueChange = {
            inputText = it
            onInputChange(inputText)
        },
        label = {
            Text(text = hint)
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.White,
            focusedLabelColor = Color.White
        )
    )
}