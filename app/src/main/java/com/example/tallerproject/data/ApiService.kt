package com.example.tallerproject.data

import com.example.tallerproject.data.model.WeatherDetailResponse
import com.example.tallerproject.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getLocationByCity(@Query("name") city: String): WeatherResponse

    @GET("https://api.open-meteo.com/v1/forecast")
    suspend fun getWeatherByLocation(
        @Query("latitude") lat: String,
        @Query("longitude") lon: String,
        @Query("current_weather") currentWeather: Boolean = true
    ): WeatherDetailResponse
}