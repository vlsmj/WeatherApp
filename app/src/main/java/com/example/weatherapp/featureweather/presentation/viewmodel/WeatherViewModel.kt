package com.example.weatherapp.featureweather.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.domain.usecase.weather.UseCasesWeather
import com.example.weatherapp.featureweather.presentation.state.CurrentWeatherState
import com.example.weatherapp.featureweather.presentation.state.ListWeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCasesWeather: UseCasesWeather,
) : ViewModel() {

    var currentWeatherState = mutableStateOf(CurrentWeatherState())
        private set

    var listWeatherState = mutableStateOf(ListWeatherState())
        private set

    fun getCurrentWeather() {
        useCasesWeather.getCurrentWeather().onEach {
            when (it) {
                is Resource.Loading -> {
                    currentWeatherState.value = currentWeatherState.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    currentWeatherState.value = currentWeatherState.value.copy(data = it.data, isLoading = false)
                }
                is Resource.Error -> {
                    currentWeatherState.value = currentWeatherState.value.copy(errorMessage = it.errorMessage, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}