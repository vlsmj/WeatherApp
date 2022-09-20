package com.example.weatherapp.featureweather.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.Validate
import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.common.util.UiText
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.domain.usecase.UseCasesWeatherApp
import com.example.weatherapp.featureweather.presentation.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCasesWeatherApp: UseCasesWeatherApp,
) : ViewModel() {

    var userState = mutableStateOf(UserState())
        private set

    fun validateLoginRegisterUserInput(username: String?, password: String?, isRegister: Boolean = false) {
        viewModelScope.launch {
            val result = Validate.usernameAndPassword(username, password)
            if (result.isNotBlank()) {
                userState.value = userState.value.copy(errorMessage = UiText.DynamicString(result))
            } else {
                if (isRegister) {
                    insertUser(username ?: "", password ?: "")
                } else {
                    getUser(username ?: "", password ?: "")
                }
            }
        }
    }

    private fun getUser(username: String, password: String) {
        useCasesWeatherApp.getUserUseCase(username, password).onEach {
            when (it) {
                is Resource.Success -> {
                    userState.value = userState.value.copy(data = it.data)
                }
                is Resource.Error -> {
                    userState.value = userState.value.copy(errorMessage = it.errorMessage)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun insertUser(username: String, password: String) {
        val newUser = User(username = username, password = password)

        useCasesWeatherApp.insertUserUseCase(newUser).onEach {
            when (it) {
                is Resource.Success -> {
                    userState.value = userState.value.copy(data = it.data)
                }
                is Resource.Error -> {
                    userState.value = userState.value.copy(errorMessage = it.errorMessage)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}