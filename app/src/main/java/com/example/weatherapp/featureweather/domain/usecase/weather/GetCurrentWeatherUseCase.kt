package com.example.weatherapp.featureweather.domain.usecase.weather

import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.domain.model.Weather
import com.example.weatherapp.featureweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {
    operator fun invoke(): Flow<Resource<Weather>> = flow {
        repository.getCurrentWeather().collect {
            emit(it)
        }
    }
}