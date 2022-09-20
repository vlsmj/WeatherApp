package com.example.weatherapp.featureweather.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.common.components.InputTextField
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.presentation.viewmodel.UserViewModel

@Composable
fun LoginRegisterScreen(
    modifier: Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    onRedirect: (user: User) -> Unit,
) {
    val state = viewModel.userState.value

    state.data?.let { user ->
        onRedirect(user)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        state.errorMessage?.let {
            Text(
                text = it.asString(),
                color = Color.Red)
        }

        InputTextField(
            hint = "Username",
            onInputChange = {
                viewModel.userState.value = viewModel.userState.value.copy(username = it)
            })

        InputTextField(
            hint = "Password",
            isSensitive = true,
            onInputChange = {
                viewModel.userState.value = viewModel.userState.value.copy(password = it)
            })

        Button(onClick = {
            viewModel.validateLoginRegisterUserInput(state.username, state.password)
        }) {
            Text(text = "Login")
        }

        Button(onClick = {
            viewModel.validateLoginRegisterUserInput(state.username, state.password, true)
        }) {
            Text(text = "Register")
        }
    }
}