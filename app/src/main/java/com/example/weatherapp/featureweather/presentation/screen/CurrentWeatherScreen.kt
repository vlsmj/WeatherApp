package com.example.weatherapp.featureweather.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.featureweather.presentation.viewmodel.WeatherViewModel

@Composable
fun CurrentWeatherScreen(
    username: String,
    modifier: Modifier,
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val state = viewModel.currentWeatherState.value

    LaunchedEffect(key1 = true) {
        viewModel.getCurrentWeather()
    }

    Box {
        state.data?.let { weather ->
            Text(text = "${weather.condition} + $username")
        }
    }
}