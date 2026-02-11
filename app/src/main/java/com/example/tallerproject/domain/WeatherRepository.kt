package com.example.tallerproject.domain

interface WeatherRepository {
    suspend fun fecthWeatherData(cityName: String): WeatherData
}