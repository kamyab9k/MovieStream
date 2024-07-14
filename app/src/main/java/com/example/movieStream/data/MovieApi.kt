package com.example.movieStream.data

import com.example.movieStream.data.repository.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/popular")
    fun getMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>
}