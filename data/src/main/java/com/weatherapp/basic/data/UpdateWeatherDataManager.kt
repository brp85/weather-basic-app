package com.weatherapp.basic.data

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.weatherapp.basic.data.api.CurrentWeatherApiInterface
import com.weatherapp.basic.data.data.WeatherInfoResponse
import com.weatherapp.basic.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Update the cache in certain duration
 */
class UpdateWeatherDataManager @Inject constructor(
    var currentWeatherApiInterface: CurrentWeatherApiInterface, appContext: Context,
    workerParams: WorkerParameters): Worker(appContext, workerParams) {

    var weatherDataStore: WeatherDataStore = WeatherDataStore(appContext)

    override fun doWork(): Result {
        val apiCall: Call<WeatherInfoResponse> = currentWeatherApiInterface
            .getCurrentWeatherInfoByLocation(
                Constants.CurrentLocation.Lat,
                Constants.CurrentLocation.Lon, "metric")

        apiCall.enqueue(object : Callback<WeatherInfoResponse> {
            override fun onResponse(
                call: Call<WeatherInfoResponse>,
                response: Response<WeatherInfoResponse>) {
                if (response.body() != null) {
                    weatherDataStore.storeWeatherData(requireNotNull(response.body()))
                }
            }
            override fun onFailure(call: Call<WeatherInfoResponse>, t: Throwable) {
                //TODO Add error log
            }
        })
        return Result.success();
    }

}