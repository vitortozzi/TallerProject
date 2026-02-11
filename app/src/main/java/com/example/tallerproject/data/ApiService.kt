package com.example.tallerproject.data

import com.example.tallerproject.data.model.WeatherDetailResponse
import com.example.tallerproject.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/search?name=city")
    suspend fun getLocationByCity(@Query("city") city: String): List<WeatherResponse>

    @GET("/v1/forecast?latitude=LAT&longitude=LON&current_weather=true")
    suspend fun getWeatherByLocation(@Query("LAT") lat: String, @Query("LON") lon: String): WeatherDetailResponse
}