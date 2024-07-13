package com.example.registerpage.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiService {
    private const val BASE_URL = "https://api.themoviedb.org/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}