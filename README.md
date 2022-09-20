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

![sc_1](https://user-images.githubusercontent.com/11737795/191381161-8b0f78ee-eaff-4450-8227-42ffad32b932.jpg) ![sc_2](https://user-images.githubusercontent.com/11737795/191381184-a47dd394-005b-40b4-9932-1ed6ccf10896.jpg) ![sc_3](https://user-images.githubusercontent.com/11737795/191381192-d360ca43-23af-419b-9293-b1060bf6cf10.jpg) ![sc_4](https://user-images.githubusercontent.com/11737795/191381195-38870577-57d8-4e72-8fc9-2ea703c1a6a0.jpg)

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
