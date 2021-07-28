package com.weatherapp.basic.ui

import android.app.Application
import android.content.Context
import com.weatherapp.basic.data.api.CurrentWeatherApiInterface
import com.weatherapp.basic.repo.RetrofitHttpClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class WeatherBasicAppModule {
    @Binds
    abstract fun provideContext(application: Application): Context

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideApiService() : CurrentWeatherApiInterface {
            return RetrofitHttpClient.client.create(CurrentWeatherApiInterface::class.java)
        }
    }
}