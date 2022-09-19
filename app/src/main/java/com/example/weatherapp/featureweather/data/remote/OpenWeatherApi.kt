package com.example.weatherapp.featureweather.data.remote

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.featureweather.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double = 14.6018990121107,
        @Query("lon") lon: Double = 120.97741193964688,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): WeatherDto
}