package com.weatherapp.basic.data.api

import com.weatherapp.basic.data.data.WeatherInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Weather API interface to get current weather details
 */
interface CurrentWeatherApiInterface {
    @GET("weather")
    fun getCurrentWeatherInfoByLocation(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Call<WeatherInfoResponse>
}