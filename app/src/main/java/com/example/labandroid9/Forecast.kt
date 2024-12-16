package com.example.labandroid9


data class Forecast(
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<ForecastItem>,
    val city: City
)

data class ForecastItem(
    val dt: Int,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val dt_txt: String
)
