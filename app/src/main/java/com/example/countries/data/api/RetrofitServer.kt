package com.example.countries.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServer {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}