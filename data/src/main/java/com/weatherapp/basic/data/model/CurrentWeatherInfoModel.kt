package com.weatherapp.basic.data.model

import com.weatherapp.basic.data.CurrentWeatherDataResponseListener

interface CurrentWeatherInfoModel {
    fun getCurrentWeatherInfo(lat: Double, lon: Double, callback: CurrentWeatherDataResponseListener)
}