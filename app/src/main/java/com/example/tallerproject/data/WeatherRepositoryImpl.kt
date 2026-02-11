package com.example.tallerproject.data

import com.example.tallerproject.domain.WeatherData
import com.example.tallerproject.domain.WeatherRepository
import retrofit2.Retrofit

class WeatherRepositoryImpl : WeatherRepository {

    val apiService = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .build()
        .create(ApiService::class.java)


    override suspend fun fecthWeatherData(cityName: String): WeatherData {

        val response = apiService.getLocationByCity(cityName)

        if (response.isEmpty()) {
            throw Exception("No weather data found")
        }

        val location = response.first()

        val weatherResponse = apiService.getWeatherByLocation(location.lat, location.lon)

        return WeatherData(
            cityName = cityName,
            temperature = weatherResponse.temperature,
            description = null
        )
    }
}