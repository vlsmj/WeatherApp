package com.example.weatherapp.featureweather.domain.usecase.user

import com.example.weatherapp.common.Validate
import com.example.weatherapp.common.util.Resource
import com.example.weatherapp.featureweather.data.repositoryimpl.FakeUserRepositoryTest
import com.example.weatherapp.featureweather.domain.model.User
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserUseCaseTest {

    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var fakeUserRepositoryTest: FakeUserRepositoryTest

    @Before
    fun setup() {
        fakeUserRepositoryTest = FakeUserRepositoryTest()
        getUserUseCase = GetUserUseCase(fakeUserRepositoryTest)

        val userSample1 = User(0, "johndoe", "12345")
        val userSample2 = User(1, "pedropenduko", "gigabyte123")
        val userSample3 = User(2, "kuyajobert", "dingdong")

        runBlocking {
            fakeUserRepositoryTest.insertUser(userSample1)
            fakeUserRepositoryTest.insertUser(userSample2)
            fakeUserRepositoryTest.insertUser(userSample3)
        }
    }

    @Test
    fun `User Log In input username empty, return error message`() {
        val result = Validate.usernameAndPassword("", "dingdong")

        assertThat(result).isEqualTo("Please enter your username.")
    }

    @Test
    fun `User Log In input password empty, return error message`() {
        val result = Validate.usernameAndPassword("kuyajobert", "")

        assertThat(result).isEqualTo("Please enter your password.")
    }

    @Test
    fun `User Log In incorrect username, return error`() = runBlocking {
        val getUser = getUserUseCase("johndoe123", "12345")

        assertThat(getUser.first()).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `User Log In incorrect password, return error`() = runBlocking {
        val getUser = getUserUseCase("johndoe", "99999")

        assertThat(getUser.first()).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `User Log In correct username and password, return user`() = runBlocking {
        val getUser = getUserUseCase("johndoe", "12345")

        assertThat(getUser.first().data).isNotNull()
    }
}





























