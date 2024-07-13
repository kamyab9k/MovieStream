package com.example.registerpage.data

import com.example.registerpage.data.repository.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/popular?api_key=947510ffdf92a1fa2f80baa6ec13cc36")
    fun getMovies(): Call<MovieResponse>
}
//interface MovieApi {
//    @GET("3/movie/popular")
//    fun getMovies(
//        @Query("947510ffdf92a1fa2f80baa6ec13cc36") apiKey: String
//    ): Call<MovieResponse>
//}