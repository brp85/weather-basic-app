package com.weatherapp.basic.data.model

import android.content.Context
import androidx.work.*
import com.weatherapp.basic.data.CurrentWeatherDataResponseListener
import com.weatherapp.basic.data.UpdateWeatherDataManager
import com.weatherapp.basic.data.WeatherDataStore
import com.weatherapp.basic.data.api.CurrentWeatherApiInterface
import com.weatherapp.basic.data.data.WeatherInfoResponse
import com.weatherapp.basic.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Call the weather API , and give callback based on success or failure
 */
class CurrentWeatherInfoModelImpl @Inject constructor(
    private val currentWeatherApiInterface: CurrentWeatherApiInterface, val context: Context)
    : CurrentWeatherInfoModel {
    var weatherDataStore: WeatherDataStore = WeatherDataStore(context)
    override fun getCurrentWeatherInfo(
        lat: Double,
        lon: Double,
        callback: CurrentWeatherDataResponseListener) {

        val apiCall: Call<WeatherInfoResponse> = currentWeatherApiInterface
            .getCurrentWeatherInfoByLocation(lat, lon, "metric")

        if (!weatherDataStore.hasWeatherDataExpired()) {
            // If the stored weather data is valid read it from local store
            callback.onSuccess(weatherDataStore.getWeatherData())
        } else {
            apiCall.enqueue(object : Callback<WeatherInfoResponse> {
                override fun onResponse(
                    call: Call<WeatherInfoResponse>,
                    response: Response<WeatherInfoResponse>) {
                    // TODO Handle invalid Response code
                    if (response.body() != null) {
                        updateWeatherData()
                        // Store the weather data
                        weatherDataStore.storeWeatherData(requireNotNull(response.body()))
                        callback.onSuccess(requireNotNull(response.body()))
                    } else {
                        callback.onFailed(response.message())
                    }
                }
                override fun onFailure(call: Call<WeatherInfoResponse>, t: Throwable) {
                    callback.onFailed(requireNotNull(t.localizedMessage))
                }
            })
        }
    }

    /**
     * Schedule to update the weather data each 2 hours
     */
    fun updateWeatherData() {
        val tag = "Get weather Periodic data"
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val periodicWork = PeriodicWorkRequest
            .Builder(UpdateWeatherDataManager::class.java,
                Constants.DataUpdateDuration.duration, TimeUnit.HOURS, 5, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .addTag(tag)
            .build()

        WorkManager.getInstance()
            .enqueueUniquePeriodicWork(tag, ExistingPeriodicWorkPolicy.KEEP, periodicWork);
    }
}