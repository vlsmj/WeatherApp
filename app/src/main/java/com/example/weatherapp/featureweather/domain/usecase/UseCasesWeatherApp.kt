package com.example.weatherapp.featureweather.domain.usecase

import com.example.weatherapp.featureweather.domain.usecase.user.GetUserUseCase
import com.example.weatherapp.featureweather.domain.usecase.user.InsertUserUseCase
import com.example.weatherapp.featureweather.domain.usecase.weather.GetAllWeatherUseCase
import com.example.weatherapp.featureweather.domain.usecase.weather.GetCurrentWeatherUseCase

data class UseCasesWeatherApp(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val getAllWeatherUseCase: GetAllWeatherUseCase,
    val getUserUseCase: GetUserUseCase,
    val insertUserUseCase: InsertUserUseCase,
)