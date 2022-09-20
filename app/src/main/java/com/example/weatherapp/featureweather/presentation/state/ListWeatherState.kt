package com.example.weatherapp.featureweather.presentation.state

import com.example.weatherapp.common.util.UiText
import com.example.weatherapp.featureweather.domain.model.Weather

data class ListWeatherState(
    val isLoading: Boolean = false,
    val data: List<Weather> = mutableListOf(),
    val errorMessage: UiText? = UiText.DynamicString(""),
)
