package com.example.tallerproject.domain

interface WeatherRepository {
    suspend fun fetchWeatherData(cityName: String): WeatherData
}