package com.example.weatherapp.common.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

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
        value = inputText,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isSensitive) {
                KeyboardType.Password
            } else {
                KeyboardType.Text
            }
        ),
        onValueChange = {
            inputText = it
            onInputChange(inputText)
        },
        label = {
            Text(text = hint)
        })
}