package com.example.weatherapp.featureweather.domain.repository

import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(): Flow<Resource<Weather>>

    suspend fun getAllWeather(): Flow<Resource<List<Weather>>>
}