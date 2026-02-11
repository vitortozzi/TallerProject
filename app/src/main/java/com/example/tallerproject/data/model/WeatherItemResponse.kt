package com.example.tallerproject.data.model

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    val results: List<WeatherItemResponse>
)

data class WeatherItemResponse(
    val latitude: Double,
    val longitude: Double,
    val name: String,
)

data class WeatherDetailResponse(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("current_weather")
    val currentWeatherResponse: CurrentWeatherResponse
)

data class CurrentWeatherResponse(
    val temperature: Double,
)