package com.example.tallerproject.data

import com.example.tallerproject.domain.WeatherData
import com.example.tallerproject.domain.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepositoryImpl : WeatherRepository {

    val apiService = Retrofit.Builder()
        .baseUrl("https://geocoding-api.open-meteo.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)


    override suspend fun fetchWeatherData(cityName: String): WeatherData {

        val response = apiService.getLocationByCity(cityName)

        if (response.results.isEmpty()) {
            throw Exception("No weather data found for city: $cityName")
        }

        val location = response.results.first()

        val weatherResponse = apiService.getWeatherByLocation(location.latitude.toString(), location.longitude.toString())

        return WeatherData(
            cityName = cityName,
            temperature = weatherResponse.currentWeatherResponse.temperature.toString(),
            description = null
        )
    }
}