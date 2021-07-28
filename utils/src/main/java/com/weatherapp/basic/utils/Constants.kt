package com.weatherapp.basic.utils

/**
 * Contains the Network service details
 */
object Constants {

    //TODO Read the network service i.e URL from a properties or some discovery service
    object NetworkService {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY = "5aab1379d6427050971aba8750aa4282"
        const val API_KEY_QUERY = "appid"
    }

    // TODO read location dynamically
    object CurrentLocation {
        const val Lat = 28.2046
        const val Lon = 77.4977
    }

    object DataUpdateDuration {
        const val duration: Long = 2
    }
}