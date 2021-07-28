package com.weatherapp.basic.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weatherapp.basic.data.data.CurrentWeatherData
import com.weatherapp.basic.data.viewmodel.CurrentWeatherInfoViewModel
import com.weatherapp.basic.data.viewmodel.CurrentWeatherInfoViewModelFactory
import com.weatherapp.basic.utils.Constants
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.weather_basic_info.*

import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factoryCurrent: CurrentWeatherInfoViewModelFactory

    private lateinit var viewModelCurrent: CurrentWeatherInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelCurrent = ViewModelProvider(this, factoryCurrent)
            .get(CurrentWeatherInfoViewModel::class.java)

        setLiveDataListeners()

        //TODO Get the location dynamically, it needs location permission
        // Or give user an option to search a city name and get location from it

        viewModelCurrent.getCurrentWeatherInfo(Constants.CurrentLocation.Lat,
            Constants.CurrentLocation.Lon)
    }

    private fun setLiveDataListeners() {

        viewModelCurrent.weatherSuccessInfoLiveData.observe(this, Observer { data ->
            setData(data)
        })

        viewModelCurrent.weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->
            //TODO Log error and show proper error dialog
        })
    }

    private fun setData(dataCurrent: CurrentWeatherData) {
        //TODO Add more info to in the UI , with proper format , animation, icons etc.
        // Show Forecast data also, may be use fragment
        date_time?.text = dataCurrent.dateTime
        temperature?.text = dataCurrent.temperature
        city_country?.text = dataCurrent.cityAndCountry
        weather_condition?.text = dataCurrent.weatherCondition
        humidity_condition?.text = dataCurrent.humidity
    }
}