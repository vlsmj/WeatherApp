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

![sc_1](https://user-images.githubusercontent.com/11737795/191381366-af649d44-ba20-4522-8c7f-d02a51e4a4b0.jpg) ![sc_2](https://user-images.githubusercontent.com/11737795/191381372-3c6e0df3-5e6a-4947-9f38-d5e02ac8a67d.jpg) ![sc_3](https://user-images.githubusercontent.com/11737795/191381383-6b687b5c-15e1-4925-969a-b7a239e58d38.jpg) ![sc_4](https://user-images.githubusercontent.com/11737795/191381390-ea4d2188-4611-4cba-9229-cc88b2f6de12.jpg)

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
