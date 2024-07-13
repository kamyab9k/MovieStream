package com.example.registerpage.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.registerpage.data.MovieApiService
import com.example.registerpage.data.repository.Movie
import com.example.registerpage.data.repository.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MovieList() {
    var movies by remember { mutableStateOf(listOf<Movie>()) }
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        MovieApiService.api.getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                loading = false
                if (response.isSuccessful) {
                    movies = response.body()?.movies.orEmpty()
                } else {
                    error = "Failed to load movies."
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                loading = false
                error = "Network error: ${t.message}"
            }
        })
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (error != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = error ?: "Unknown error", color = MaterialTheme.colors.error)
        }
    } else {
        Column {
            if (selectedMovie != null) {
                MovieDetail(movie = selectedMovie!!, onBack = { selectedMovie = null })
            } else {
                LazyColumn {
                    items(movies) { movie ->
                        MovieItem(movie = movie, onClick = { selectedMovie = it })
                    }
                }
            }
        }
    }
}