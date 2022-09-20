# WeatherApp
WeatherApp is a simple Weather watch application that uses MVVM + Retrofit + Room and Clean Architecture. The application uses data from OpenWeather API. See documentation here: https://openweathermap.org/current

# Usage
This application requires API KEY from OpenWeather. For you to be able to access the endpoints, you must have a API_KEY field in your local.properties file. Insert a new field with "API_KEY" (without quotes):

e.g.
API_KEY={API KEY here}

Put the API KEY provided to you, or you can put your own API KEY you got from OpenWeather API. Rebuild then run the application.

# Functionality Features
- Log In and Registration with Room integration. This saves the user credentials into the local DB.
- LocationManager access with GPS to get the most accurate location possible. By doing this, the application can get the latitude and longitude data as well as some of the addresses available, to be passed into the API call.
- Permission checking of Location access.
- OpenWeather API integration.
- Bottom navigation tab.
- Showing a list of fetched weather data from previous app launches.

![sc_1](https://user-images.githubusercontent.com/11737795/191380535-b1cf47df-8afd-4697-9e5f-54d4a5d34e7e.jpg) ![sc_2](https://user-images.githubusercontent.com/11737795/191380545-722cdc89-718b-45d7-bd49-bcfe66436248.jpg) ![sc_3](https://user-images.githubusercontent.com/11737795/191380553-5f10e31e-face-429b-80c0-17d2625a709c.jpg) ![sc_4](https://user-images.githubusercontent.com/11737795/191380567-92181a8f-3cfe-4800-941f-45903da16a7a.jpg)

# Core Features
- Jetpack Compose
- Kotlin
- MVVM
- Kotlin Coroutines
- Repository
- Clean Architecture
- Use Case
- Room
- Hilt
