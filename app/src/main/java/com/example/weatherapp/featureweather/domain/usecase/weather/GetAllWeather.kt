package com.example.weatherapp.featureweather.domain.usecase.weather

import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.domain.model.Weather
import com.example.weatherapp.featureweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllWeather @Inject constructor(
    private val repository: WeatherRepository,
) {
    operator fun invoke(): Flow<Resource<List<Weather>>> = flow {
        repository.getAllWeather().collect {
            emit(it)
        }
    }
}