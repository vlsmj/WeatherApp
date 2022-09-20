package com.example.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.common.Constants.BASE_URL
import com.example.weatherapp.common.PrefManager
import com.example.weatherapp.featureweather.data.datasource.UserDao
import com.example.weatherapp.featureweather.data.datasource.WeatherDao
import com.example.weatherapp.featureweather.data.datasource.WeatherDatabase
import com.example.weatherapp.featureweather.data.remote.OpenWeatherApi
import com.example.weatherapp.featureweather.domain.repository.UserRepository
import com.example.weatherapp.featureweather.domain.repository.WeatherRepository
import com.example.weatherapp.featureweather.domain.usecase.UseCasesWeatherApp
import com.example.weatherapp.featureweather.domain.usecase.user.GetUserUseCase
import com.example.weatherapp.featureweather.domain.usecase.user.InsertUserUseCase
import com.example.weatherapp.featureweather.domain.usecase.weather.GetAllWeatherUseCase
import com.example.weatherapp.featureweather.domain.usecase.weather.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(app: Application): WeatherDatabase {
        return Room.databaseBuilder(
            app,
            WeatherDatabase::class.java, "db_weather")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): OpenWeatherApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @Provides
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }

    @Provides
    fun provideUserDao(database: WeatherDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun providesPrefManager(application: Application): PrefManager = PrefManager(application)

    @Provides
    @Singleton
    fun provideWeatherUseCases(weatherRepository: WeatherRepository, userRepository: UserRepository) = UseCasesWeatherApp(
        GetCurrentWeatherUseCase(weatherRepository),
        GetAllWeatherUseCase(weatherRepository),
        GetUserUseCase(userRepository),
        InsertUserUseCase(userRepository)
    )
}
















