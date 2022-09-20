package com.example.weatherapp.featureweather.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val username: String,
    val password: String,
) : Serializable