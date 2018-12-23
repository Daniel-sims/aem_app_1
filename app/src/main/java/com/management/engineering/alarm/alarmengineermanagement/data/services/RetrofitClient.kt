package com.management.engineering.alarm.alarmengineermanagement.data.services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Toda esta clase necesita ser cambiada para usar la inyección de dependencia porque es jodidamente absymal
object RetrofitClient {

    var retrofit: Retrofit? = null
    var authenticatedRetrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            //TODO While release in Google Play Change the Level to NONE
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .build()

            retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit
    }

    // Authenticated retrofit doesn't follow a singleton pattern as this would mean logging into a different user would
    // keep the same token
    fun getClient(baseUrl: String, token: String): Retrofit? {
        //TODO While release in Google Play Change the Level to NONE
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(authenticationInterceptor(token))
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

        authenticatedRetrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return authenticatedRetrofit
    }

    private fun authenticationInterceptor(token: String) = Interceptor { chain ->
        if (token.isNotEmpty()) {
            chain.proceed(chain.request().newBuilder()
                    .addHeader("Authorization", "Token $token")
                    .build())
        } else {
            chain.proceed(chain.request().newBuilder()
                    .build())
        }
    }
}