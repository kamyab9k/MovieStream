package com.example.movieStream.data.repository

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Response

data class Series(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("vote_average") val vote_average: Number,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("first_air_date") val first_air_date: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("vote_count") val vote_count: Int,
)

fun parseSeriesResponse(response: Response?): List<Series> {
    return response?.body?.string()?.let {
        Gson().fromJson(it, SeriesResponse::class.java).results
    } ?: emptyList()
}
