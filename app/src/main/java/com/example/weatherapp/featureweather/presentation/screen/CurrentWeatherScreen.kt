package com.example.weatherapp.featureweather.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.featureweather.presentation.viewmodel.WeatherViewModel

@Composable
fun CurrentWeatherScreen(
    username: String,
    latitude: String,
    longitude: String,
    address: String,
    modifier: Modifier,
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val state = viewModel.currentWeatherState.value

    val mUsername by remember {
        mutableStateOf(username)
    }

    val mLatitude by remember {
        mutableStateOf(latitude)
    }

    val mLongitude by remember {
        mutableStateOf(longitude)
    }

    val mAddress by remember {
        mutableStateOf(address)
    }

    LaunchedEffect(Unit) {
        viewModel.getCurrentWeather(mLatitude, mLongitude, mAddress)
    }

    Column {
        state.data?.let { weather ->
            Text(text = "${weather.condition}")
            Text(text = "${mUsername}")
            Text(text = "${weather.latitude}")
            Text(text = "${weather.longitude}")
            Text(text = "${weather.location}")
        }
    }
}