package com.example.weatherapp.featureweather.domain.usecase.user

import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(username: String, password: String): Flow<Resource<User>> = flow {
        repository.getUser(username, password).collect {
            emit(it)
        }
    }
}