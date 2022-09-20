package com.example.weatherapp.di

import com.example.weatherapp.featureweather.data.repositoryimpl.UserRepositoryImpl
import com.example.weatherapp.featureweather.data.repositoryimpl.WeatherRepositoryImpl
import com.example.weatherapp.featureweather.domain.repository.UserRepository
import com.example.weatherapp.featureweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepositoryImpl(
        weatherRepositoryImpl: WeatherRepositoryImpl,
    ): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindUserRepositoryImpl(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository
}