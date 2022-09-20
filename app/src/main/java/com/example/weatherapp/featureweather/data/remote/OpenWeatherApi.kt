package com.example.weatherapp.featureweather.data.remote

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.common.Constants.DEFAULT_LATITUDE
import com.example.weatherapp.common.Constants.DEFAULT_LONGITUDE
import com.example.weatherapp.featureweather.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double = DEFAULT_LATITUDE,
        @Query("lon") lon: Double = DEFAULT_LONGITUDE,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
    ): WeatherDto
}