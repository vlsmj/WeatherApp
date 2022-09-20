package com.example.weatherapp.featureweather.data.repositoryimpl

import com.example.weatherapp.R
import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.common.util.UiText
import com.example.weatherapp.featureweather.data.datasource.WeatherDao
import com.example.weatherapp.featureweather.data.remote.OpenWeatherApi
import com.example.weatherapp.featureweather.data.remote.dto.toWeather
import com.example.weatherapp.featureweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi,
    private val weatherDao: WeatherDao,
) : WeatherRepository {

    override suspend fun getCurrentWeather() = flow {
        try {
            emit(Resource.Loading())

            val newCurrentWeather = api.getCurrentWeather()
            weatherDao.insertWeather(newCurrentWeather.toWeather())

            emit(Resource.Success(weatherDao.getAllWeather()[0]))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }

    override suspend fun getAllWeather() = flow {
        try {
            emit(Resource.Success(weatherDao.getAllWeather()))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }
}