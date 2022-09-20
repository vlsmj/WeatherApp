package com.example.weatherapp.featureweather.data.repositoryimpl

import com.example.weatherapp.R
import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.common.util.UiText
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeUserRepositoryTest : UserRepository {

    private val fakeUsers = mutableListOf<User>()

    override suspend fun getUser(username: String, password: String): Flow<Resource<User>> {
        val user = fakeUsers.find {
            it.username == username && it.password == password
        }

        return flow {
            if (user != null) {
                emit(Resource.Success(user))
            } else {
                emit(Resource.Error(UiText.StringResource(R.string.error_user_not_found)))
            }
        }
    }

    override suspend fun insertUser(user: User): Flow<Resource<User>> {
        fakeUsers.add(user)

        return flow {
            emit(Resource.Success(user))
        }
    }
}