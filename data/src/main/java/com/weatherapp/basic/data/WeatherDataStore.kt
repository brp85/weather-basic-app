package com.weatherapp.basic.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.weatherapp.basic.data.data.WeatherInfoResponse
import com.weatherapp.basic.utils.Constants
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Use to store the weather data in sharedPreferences
 */
class WeatherDataStore  constructor(private val sharedPreferences: SharedPreferences) {

    internal constructor(context: Context) : this(
        context.getSharedPreferences(
            PREFS_FILE,
            Context.MODE_PRIVATE
        )
    )

    companion object {
        private const val PREFS_FILE = "weather_data_shared_prefs"
        const val WEATHER_DATA_KEY = "weather_data_key"
        const val WEATHER_UPDATE_TIME_KEY = "weather_update_time_key"
    }

    /**
     * Store the weather data
     */
    fun storeWeatherData(data: WeatherInfoResponse) {
        var prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(data)
        prefsEditor.putString(WEATHER_DATA_KEY, json)
        prefsEditor.putLong(WEATHER_UPDATE_TIME_KEY, Date().time)
        prefsEditor.commit()
    }

    /**
     * Get the stored weather data
     */
    fun getWeatherData() : WeatherInfoResponse {
        val gson = Gson()
        val json: String? = sharedPreferences.getString(WEATHER_DATA_KEY, null)
        val weatherInfoResponse: WeatherInfoResponse = gson.fromJson(
            json,
            WeatherInfoResponse::class.java
        )
        return weatherInfoResponse
    }

    /**
     * Check if the data has expired
     */
    fun hasWeatherDataExpired() : Boolean {
        val maxStoredLimit = TimeUnit.HOURS.toMillis(Constants.DataUpdateDuration.duration)
        val lastUpdatedTime: Long = sharedPreferences.getLong(WEATHER_UPDATE_TIME_KEY, 0)
        val diffTime = Math.abs(System.currentTimeMillis() - lastUpdatedTime)
        if (diffTime > maxStoredLimit) {
            return true
        }
        return false
    }
}