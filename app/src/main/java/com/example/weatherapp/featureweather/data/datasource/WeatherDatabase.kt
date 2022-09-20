package com.example.weatherapp.featureweather.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.domain.model.Weather

@Database(entities = [Weather::class, User::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    abstract fun userDao(): UserDao
}