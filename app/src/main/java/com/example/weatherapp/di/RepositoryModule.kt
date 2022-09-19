package com.example.weatherapp.di

import com.example.weatherapp.featureweather.data.repositoryimpl.WeatherRepositoryImpl
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
    abstract fun bindCoinRepositoryImpl(
        coinRepositoryImpl: WeatherRepositoryImpl,
    ): WeatherRepository
}