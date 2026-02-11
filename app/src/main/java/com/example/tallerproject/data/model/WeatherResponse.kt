package com.example.tallerproject.data.model

import okhttp3.Address

data class WeatherResponse(
    val lat: String,
    val lon: String,
    val address: WeatherAddressResponse
)

data class WeatherAddressResponse(
    val city: String,
    val state: String,
    val country: String,
)

data class WeatherDetailResponse(
    val temperature: String,
)