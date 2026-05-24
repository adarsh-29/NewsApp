package com.example.newsapp.core.network

import com.example.newsapp.core.common.NetworkResult
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall( apiCall: suspend () -> Response<T>): NetworkResult<T> {

    return try {

        val response = apiCall()

        if (response.isSuccessful) {

            response.body()?.let {

                NetworkResult.Success(it)

            } ?: NetworkResult.Error(
                message = "Empty response body"
            )

        } else {

            NetworkResult.Error(
                message = response.message(),
                code = response.code()
            )
        }

    } catch (e: IOException) {

        NetworkResult.Error(
            message = "No internet connection"
        )

    } catch (e: Exception) {

        NetworkResult.Error(
            message = e.localizedMessage ?: "Unknown error"
        )
    }
}