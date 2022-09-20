# WeatherApp
WeatherApp is a simple Weather watch application that uses MVVM + Retrofit + Room and Clean Architecture. The application uses data from OpenWeather API. See documentation here: https://openweathermap.org/current

# Usage
This application requires API KEY from OpenWeather. For you to be able to the access endpoints, you must have a API_KEY field in your local.properties file. Insert a new field with "API_KEY" (without quotes):

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

![sc_1](https://user-images.githubusercontent.com/11737795/191380422-4c04291b-84d0-4b20-94f3-05a586713543.jpg)![sc_2](https://user-images.githubusercontent.com/11737795/191380428-45848787-c03a-419f-868f-1da244e97add.jpg)![sc_3](https://user-images.githubusercontent.com/11737795/191380431-f639b73d-4e53-43a3-8faa-d0aeea5d3aac.jpg)![sc_4](https://user-images.githubusercontent.com/11737795/191380436-51f4c568-f3e4-4de3-bd17-f3412511a554.jpg)

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
