# WeatherApp
WeatherApp is a simple Weather watch application that uses MVVM + Retrofit + Room and Clean Architecture. The application uses data from OpenWeather API. See documentation here: https://openweathermap.org/current

# Usage
This application requires API KEY from OpenWeather. For you to be able to the access endpoints, you must have a API_KEY field in your local.properties file. Insert a new field with "API_KEY" (key without quotes):

e.g.
API_KEY={API KEY here}

Put the API KEY provided to you, or you can put your own API KEY you got from OpenWeather API.

# Functionality Features
- Log In and Registration with Room integration. This saves the user credentials into the local DB.
- LocationManager access with GPS to get the most accurate location possible. By doing this, the application can get the latitude and longitude data as well as some of the addresses available, to be passed into the API call.
- Permission checking of Location access.
- OpenWeather API integration.
- Bottom navigation tab.
- Showing a list of fetched weather data from previous app launches.

![sc_1](https://user-images.githubusercontent.com/11737795/191379014-a27b2d1e-a3eb-4c32-9eda-131080d6d141.jpg)![sc_2](https://user-images.githubusercontent.com/11737795/191379021-1a9a55ea-17f0-4991-a236-285bfe74f4a7.jpg)![sc_3](https://user-images.githubusercontent.com/11737795/191379028-6fd51bcb-d1fd-4771-9f24-decce839abe0.jpg)![sc_4](https://user-images.githubusercontent.com/11737795/191379033-fcca9a4a-a9db-40f7-b38e-c3107e2981eb.jpg)

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
