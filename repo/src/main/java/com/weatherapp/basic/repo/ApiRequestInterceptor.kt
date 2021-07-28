package com.weatherapp.basic.repo

import com.weatherapp.basic.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Observes the request and the response and modifying it
 */
class ApiRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request().url()
            .newBuilder()
            .addQueryParameter(
                Constants.NetworkService.API_KEY_QUERY,
                Constants.NetworkService.API_KEY
            )
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request);
    }
}