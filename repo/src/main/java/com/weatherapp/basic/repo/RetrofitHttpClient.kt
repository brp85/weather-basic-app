package com.weatherapp.basic.repo

import com.google.gson.GsonBuilder
import com.weatherapp.basic.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create the Retrofit Client instance
 */
object RetrofitHttpClient  {
    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    val client: Retrofit
        get() {
            if (retrofit == null) {
                    if (retrofit == null) {
                        val httpClient = OkHttpClient.Builder()
                            .addInterceptor(ApiRequestInterceptor())
                        val client = httpClient.build()
                        retrofit = Retrofit.Builder()
                            .baseUrl(Constants.NetworkService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(client)
                            .build()
                    }
            }
            return retrofit!!
        }
}