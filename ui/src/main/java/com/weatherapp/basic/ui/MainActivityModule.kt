package com.weatherapp.basic.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weatherapp.basic.data.model.CurrentWeatherInfoModel
import com.weatherapp.basic.data.model.CurrentWeatherInfoModelImpl
import com.weatherapp.basic.data.viewmodel.CurrentWeatherInfoViewModel
import com.weatherapp.basic.data.viewmodel.CurrentWeatherInfoViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindMainViewModel(viewModelCurrent: CurrentWeatherInfoViewModel): ViewModel

    @Binds
    abstract fun bindModel(weatherInfoShowModelImpl: CurrentWeatherInfoModelImpl): CurrentWeatherInfoModel

    @Binds
    abstract fun bindWeatherInfoViewModelFactory(factoryCurrent: CurrentWeatherInfoViewModelFactory):
            ViewModelProvider.Factory
}