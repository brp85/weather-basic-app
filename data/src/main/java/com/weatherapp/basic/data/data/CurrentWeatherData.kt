package com.weatherapp.basic.data.data

/**
 * Data class which holds the current weather details
 */
data class CurrentWeatherData(
    var dateTime: String = "",
    var temperature: String = "0",
    var cityAndCountry: String = "",
    var weatherCondition: String = "",
    var humidity: String = ""
)