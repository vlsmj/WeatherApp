package com.example.weatherapp.featureweather.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.featureweather.domain.model.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    suspend fun getAllWeather(): List<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Weather)
}