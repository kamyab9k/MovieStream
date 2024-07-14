package com.example.movieStream.data

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object OkHttpClientInstance {

    private val client = OkHttpClient()

    fun getPopularMovies(): Response? {
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NDc1MTBmZmRmOTJhMWZhMmY4MGJhYTZlYzEzY2MzNiIsIm5iZiI6MTcyMDg3NjM1Ny44NzE1NjMsInN1YiI6IjY0MDA5MDhmOTY1M2Y2MDA4NTNkOTc3OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.73rS1dmjLK_c4Tods7RU_qcunzJ4ig56T8DI5ezgZ5o")
            .build()

        return try {
            client.newCall(request).execute()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun getPopularSeries(): Response? {
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/tv/popular?language=en-US&page=1")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NDc1MTBmZmRmOTJhMWZhMmY4MGJhYTZlYzEzY2MzNiIsIm5iZiI6MTcyMDg3NjM1Ny44NzE1NjMsInN1YiI6IjY0MDA5MDhmOTY1M2Y2MDA4NTNkOTc3OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.73rS1dmjLK_c4Tods7RU_qcunzJ4ig56T8DI5ezgZ5o")
            .build()

        return try {
            client.newCall(request).execute()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
