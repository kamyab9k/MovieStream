package com.example.registerpage.data.repository

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Response

data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("score") val vote_average: Double,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val release: String,
)

fun parseMoviesResponse(response: Response?): List<Movie> {
    return response?.body?.string()?.let {
        Gson().fromJson(it, MovieResponse::class.java).results
    } ?: emptyList()
}
