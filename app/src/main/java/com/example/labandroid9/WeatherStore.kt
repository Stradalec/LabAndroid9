package com.example.labandroid9

class WeatherStore {
    var weathers: List<ForecastItem>? = null

    fun updateWeathers(newWeathers: List<ForecastItem>) {
        weathers = newWeathers
    }
}

