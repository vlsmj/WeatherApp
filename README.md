# WeatherApp
WeatherApp is a simple Weather watch application that uses MVVM + Retrofit + Room and Clean Architecture. The application uses data from OpenWeather API. See documentation here: https://openweathermap.org/current.

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

![sc_1](https://user-images.githubusercontent.com/11737795/191381665-f45218d0-0ed7-4552-94aa-f10e5b742590.jpg) ![sc_2](https://user-images.githubusercontent.com/11737795/191381671-813ef637-7bc9-4a35-9a36-1f530722c4d9.jpg) 

![sc_3](https://user-images.githubusercontent.com/11737795/191381690-0684116b-a57e-4c23-8027-1692c99e1cf3.jpg) ![sc_4](https://user-images.githubusercontent.com/11737795/191381700-9a8ed576-6192-46ef-94bb-7bc5a74dbea2.jpg)

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
