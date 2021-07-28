package com.weatherapp.basic.data.model

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.mockito.Mockito.*
import com.weatherapp.basic.data.CurrentWeatherDataResponseListener
import com.weatherapp.basic.data.WeatherDataStore
import com.weatherapp.basic.data.api.CurrentWeatherApiInterface
import com.weatherapp.basic.data.data.WeatherInfoResponse
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import retrofit2.Call


@RunWith(AndroidJUnit4::class)
class CurrentWeatherInfoModelImplTest {
    @MockK
    lateinit var context: Context
    @MockK
    lateinit var currentWeatherApiInterface: CurrentWeatherApiInterface
    @MockK
    lateinit var weatherDataStore: WeatherDataStore
    @MockK
    lateinit var currentWeatherDataResponseListener: CurrentWeatherDataResponseListener
    @MockK
    lateinit var apiCall: Call<WeatherInfoResponse>
    @MockK
    lateinit var weatherInfoResponse: WeatherInfoResponse

    private lateinit var currentWeatherInfoModelImpl: CurrentWeatherInfoModelImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        currentWeatherInfoModelImpl =
            CurrentWeatherInfoModelImpl(currentWeatherApiInterface, context)
        currentWeatherInfoModelImpl.weatherDataStore = weatherDataStore
    }

    @Test
    fun `given location details, when getCurrentWeatherData called, then get data from cache if not expired`() {
        // Given
        `when`(
            currentWeatherApiInterface.getCurrentWeatherInfoByLocation(
                27.00,
                77.12, "metric"
            )
        ).thenReturn(apiCall)
        `when`(weatherDataStore.hasWeatherDataExpired()).thenReturn(false)
        `when`(weatherDataStore.getWeatherData()).thenReturn(weatherInfoResponse)

        // When
        currentWeatherInfoModelImpl.getCurrentWeatherInfo(
            27.00, 77.12,
            currentWeatherDataResponseListener
        )

        // Then
        Mockito.verify(currentWeatherDataResponseListener).onSuccess(weatherInfoResponse)
    }

    @Test
    fun `given location details, when getCurrentWeatherData called, then get data from network if cache expired`() {
        // Given
        `when`(
            currentWeatherApiInterface.getCurrentWeatherInfoByLocation(
                27.00,
                77.12, "metric"
            )
        ).thenReturn(apiCall)
        `when`(weatherDataStore.hasWeatherDataExpired()).thenReturn(true)

        doAnswer {
            val callback = it.arguments[0] as CurrentWeatherDataResponseListener
            callback.onSuccess(weatherInfoResponse)
        }.`when`(apiCall).enqueue(any())

        // When
        currentWeatherInfoModelImpl.getCurrentWeatherInfo(
            27.00, 77.12,
            currentWeatherDataResponseListener
        )

        // Then
        verify(weatherDataStore).storeWeatherData(weatherInfoResponse)
    }
}