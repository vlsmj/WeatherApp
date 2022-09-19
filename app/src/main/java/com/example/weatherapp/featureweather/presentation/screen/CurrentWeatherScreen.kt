package com.example.weatherapp.featureweather.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.featureweather.presentation.viewmodel.WeatherViewModel

@Composable
fun CurrentWeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val state = viewModel.currentWeatherState.value

    LaunchedEffect(key1 = true) {
        viewModel.getCurrentWeather()
    }

    Box {
        state.data?.let { weather ->
            Text(text = "${weather.condition}")
        }
    }
}