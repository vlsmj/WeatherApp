package com.example.weatherapp.featureweather.data.remote.dto

data class ConditionDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)