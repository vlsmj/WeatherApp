package com.example.weatherapp.featureweather.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.common.components.WeatherListItem
import com.example.weatherapp.featureweather.presentation.viewmodel.WeatherViewModel

@Composable
fun ListWeatherScreen(
    modifier: Modifier,
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val state = viewModel.listWeatherState.value

    LaunchedEffect(Unit) {
        viewModel.getAllWeather()
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.data) { weather ->
            WeatherListItem(modifier = Modifier.fillMaxWidth(), weather)
        }
    }
}