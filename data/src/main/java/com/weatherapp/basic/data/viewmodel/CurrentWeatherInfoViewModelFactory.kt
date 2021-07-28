package com.weatherapp.basic.data.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weatherapp.basic.data.model.CurrentWeatherInfoModel
import javax.inject.Inject

/**
 * This is called to get the CurrentWeatherInfoModel
 */
class CurrentWeatherInfoViewModelFactory @Inject constructor(private val param: CurrentWeatherInfoModel,
                                                             var context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CurrentWeatherInfoModel::class.java, Context::class.java)
            .newInstance(param, context)
    }
}