package com.example.todos.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private var retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    fun getService(): Service = retrofit.create(Service::class.java)
    private fun getClient() =
        OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(getHttpLoggingInterceptor())
            .build()


    private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )
}