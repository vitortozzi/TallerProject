package com.example.tallerproject.presentation

import com.example.tallerproject.domain.WeatherData

sealed class WeatherUiState {
    object Initial : WeatherUiState()
    object Empty : WeatherUiState()

    object Loading : WeatherUiState()
    data class Success(val weatherData: WeatherData) : WeatherUiState()
    object Error : WeatherUiState()
}