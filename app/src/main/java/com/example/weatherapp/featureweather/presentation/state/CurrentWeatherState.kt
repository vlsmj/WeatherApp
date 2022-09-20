package com.example.weatherapp.featureweather.presentation.state

import com.example.weatherapp.common.util.UiText
import com.example.weatherapp.featureweather.domain.model.Weather

data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val data: Weather? = null,
    val errorMessage: UiText? = UiText.DynamicString(""),
)
