package com.example.weatherapp.featureweather.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
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

        Image(painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo",
            modifier = Modifier.size(96.dp))

        Spacer(modifier = Modifier.height(16.dp))

        state.errorMessage?.let {
            Text(
                text = it.asString(),
                fontSize = 12.sp,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        InputTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = "Username",
            onInputChange = {
                viewModel.userState.value = viewModel.userState.value.copy(username = it)
            })

        InputTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = "Password",
            isSensitive = true,
            onInputChange = {
                viewModel.userState.value = viewModel.userState.value.copy(password = it)
            })

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            onClick = {
                viewModel.validateLoginRegisterUserInput(state.username, state.password)
            }) {
            Text(text = "Log In")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            onClick = {
                viewModel.validateLoginRegisterUserInput(state.username, state.password, true)
            }) {
            Text(text = "Register Credentials")
        }
    }
}