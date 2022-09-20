package com.example.weatherapp.featureweather.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.domain.usecase.UseCasesWeatherApp
import com.example.weatherapp.featureweather.presentation.state.CurrentWeatherState
import com.example.weatherapp.featureweather.presentation.state.ListWeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCasesWeatherApp: UseCasesWeatherApp,
) : ViewModel() {

    var currentWeatherState = mutableStateOf(CurrentWeatherState())
        private set

    var listWeatherState = mutableStateOf(ListWeatherState())
        private set

    fun getCurrentWeather(latitude: String, longitude: String, address: String) {
        useCasesWeatherApp.getCurrentWeatherUseCase(latitude.toDouble(), longitude.toDouble()).onEach { it ->
            when (it) {
                is Resource.Loading -> {
                    currentWeatherState.value = currentWeatherState.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    currentWeatherState.value = currentWeatherState.value.copy(
                        data = it.data.apply {
                            this?.let { weather ->
                                weather.location = address
                                weather.latitude = latitude.toDouble()
                                weather.longitude = longitude.toDouble()
                            }
                        }, isLoading = false)
                }
                is Resource.Error -> {
                    currentWeatherState.value = currentWeatherState.value.copy(errorMessage = it.errorMessage, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllWeather() {
        useCasesWeatherApp.getAllWeatherUseCase().onEach { it ->
            when (it) {
                is Resource.Success -> {
                    listWeatherState.value = listWeatherState.value.copy(data = it.data ?: mutableListOf())
                }
                is Resource.Error -> {
                    listWeatherState.value = listWeatherState.value.copy(errorMessage = it.errorMessage)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}