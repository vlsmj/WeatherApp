package com.example.weatherapp.featureweather.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.featureweather.presentation.viewmodel.WeatherViewModel

@Composable
fun ListWeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    Box {
        Text(text = "List weather screen")
    }
}