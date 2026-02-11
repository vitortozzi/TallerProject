package com.example.tallerproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tallerproject.domain.WeatherData
import com.example.tallerproject.ui.theme.TallerProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TallerProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        WeatherSearch(
            onSearch = viewModel::fetchWeatherData
        )
        WeatherInfo(
            uiState = uiState
        )
    }
}

@Composable
fun WeatherSearch(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var typedText by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Text(
            "Please insert a city name below:"
        )
        TextField(
            value = typedText,
            onValueChange = { text ->
                typedText = text
                onSearch(text)
            }
        )
    }
}

@Composable
fun WeatherInfo(
    modifier: Modifier = Modifier,
    uiState: WeatherUiState,
) {
    when(uiState) {
        is WeatherUiState.Initial -> {}
        is WeatherUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is WeatherUiState.Success -> {
            Column(
                modifier = modifier.padding(top = 24.dp)
            ) {
                Row {
                    Text(text = "City name:")
                    Text(text = uiState.weatherData.cityName)
                }
                Row {
                    Text(text = "temperature:")
                    Text(text = uiState.weatherData.temperature)
                }
                if(uiState.weatherData.description != null) {
                    Row {
                        Text(text = "Weather Description:")
                        Text(text = uiState.weatherData.description)
                    }
                }
            }
        }
        is WeatherUiState.Empty -> {
            Text(text = "No weather info found")
        }
        is WeatherUiState.Error -> {
            Text(text = "Error")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherSearchPreview() {
    TallerProjectTheme {
        WeatherSearch(onSearch = {})
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherInfoPreview() {
    TallerProjectTheme {
        WeatherInfo(uiState = WeatherUiState.Success(WeatherData("London", "25°C", "Sunny")))
    }
}