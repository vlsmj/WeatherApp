package com.example.weatherapp.featureweather.domain.usecase.user

import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(user: User): Flow<Resource<User>> = flow {
        repository.insertUser(user).collect {
            emit(it)
        }
    }
}