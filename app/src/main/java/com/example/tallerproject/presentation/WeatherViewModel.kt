package com.example.tallerproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tallerproject.data.WeatherRepositoryImpl
import com.example.tallerproject.domain.WeatherData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private var _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Initial)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    val repository = WeatherRepositoryImpl()


    private var searchJob: Job? = null

    fun fetchWeatherData(cityName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            _uiState.value = WeatherUiState.Loading
            try {
                val response = repository.fetchWeatherData(cityName)
                _uiState.value = WeatherUiState.Success(WeatherData(cityName, response.temperature, response.description))
            } catch (_: Exception) {
                _uiState.value = WeatherUiState.Error
            }
        }
    }

}