package com.weatherapp.basic.data.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weatherapp.basic.utils.formatTime
import com.weatherapp.basic.data.CurrentWeatherDataResponseListener
import com.weatherapp.basic.data.R
import com.weatherapp.basic.data.data.CurrentWeatherData
import com.weatherapp.basic.data.data.WeatherInfoResponse
import com.weatherapp.basic.data.model.CurrentWeatherInfoModel
import javax.inject.Inject

/**
 * Model which contains the live weather data success or failure and post it
 */
class CurrentWeatherInfoViewModel @Inject constructor(var modelCurrent:
                                                      CurrentWeatherInfoModel,
                                                      var context: Context) : ViewModel() {

    val weatherSuccessInfoLiveData = MutableLiveData<CurrentWeatherData>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()

    fun getCurrentWeatherInfo(lat: Double, lon: Double) {

        modelCurrent.getCurrentWeatherInfo(lat, lon, object :
            CurrentWeatherDataResponseListener {

            override fun onSuccess(data: WeatherInfoResponse) {
                val weatherData = CurrentWeatherData(
                    dateTime = data.dt.formatTime(),
                    temperature = data.main.temp.toString() + " °C",
                    cityAndCountry = "${data.name}, ${data.sys.country}",
                    weatherCondition = context.getString(R.string.weather_condition) + data.weather[0]
                        .description.toString().capitalize(),
                    humidity = context.getString(R.string.humidity) + "${data.main.humidity}%"
                )
                weatherSuccessInfoLiveData.postValue(weatherData)
            }

            override fun onFailed(errorMessage: String) {
                weatherInfoFailureLiveData.postValue(errorMessage)
            }
        })
    }
}