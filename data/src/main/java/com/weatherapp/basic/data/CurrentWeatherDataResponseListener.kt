package com.weatherapp.basic.data

import com.weatherapp.basic.data.data.WeatherInfoResponse

/**
 * Handles Callback for current weather API call
 */
interface CurrentWeatherDataResponseListener {
    fun onSuccess(weatherData: WeatherInfoResponse)
    fun onFailed(errorMessage: String)
}
