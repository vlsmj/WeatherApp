package com.example.weatherapp.featureweather.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.featureweather.domain.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    suspend fun getUser(
        username: String,
        password: String,
    ): User?

    @Insert
    suspend fun insertUser(user: User)
}