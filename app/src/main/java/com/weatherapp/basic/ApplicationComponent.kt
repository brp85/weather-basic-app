package com.weatherapp.basic

import android.app.Application
import com.weatherapp.basic.data.viewmodel.CurrentWeatherInfoViewModel
import com.weatherapp.basic.ui.WeatherBasicAppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, WeatherBasicAppModule::class])
interface ApplicationComponent : AndroidInjector<WeatherBasicApplication> {

    fun inject(currentWeatherInfoViewModel: CurrentWeatherInfoViewModel)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}