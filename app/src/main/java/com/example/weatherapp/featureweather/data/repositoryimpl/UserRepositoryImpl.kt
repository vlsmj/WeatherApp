package com.example.weatherapp.featureweather.data.repositoryimpl

import com.example.weatherapp.R
import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.common.util.UiText
import com.example.weatherapp.featureweather.data.datasource.UserDao
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : UserRepository {

    override suspend fun getUser(username: String, password: String) = flow {
        try {
            val getUser = userDao.getUser(username, password)

            if (getUser != null) {
                emit(Resource.Success(getUser))
            } else {
                emit(Resource.Error(UiText.StringResource(R.string.error_user_not_found)))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }

    override suspend fun insertUser(user: User) = flow {
        try {
            val getUser = userDao.getUser(user.username, user.password)

            if (getUser == null) {
                userDao.insertUser(user)
                emit(Resource.Success(user))
            } else {
                emit(Resource.Error(UiText.StringResource(R.string.error_user_exists)))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }
}