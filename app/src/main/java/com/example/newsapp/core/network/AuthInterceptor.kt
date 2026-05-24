package com.example.newsapp.core.network

import com.example.newsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader(
                "X-Api-Key",
                BuildConfig.NEWS_API_KEY
            )
            .build()

        return chain.proceed(request)
    }
}