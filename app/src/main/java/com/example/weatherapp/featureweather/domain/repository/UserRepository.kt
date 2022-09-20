package com.example.weatherapp.featureweather.domain.repository

import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUser(username: String, password: String): Flow<Resource<User>>

    suspend fun insertUser(user: User): Flow<Resource<User>>
}