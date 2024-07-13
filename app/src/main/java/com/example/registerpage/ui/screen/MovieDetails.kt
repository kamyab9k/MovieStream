package com.example.registerpage.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.registerpage.data.repository.Movie

@Composable
fun MovieDetail(movie: Movie, onBack: () -> Unit) {
    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = movie.title, color = Color.Black)
        Image(
            painter = rememberImagePainter(IMAGE_BASE_URL + movie.poster),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(text = "Score: ${movie.vote_average}", color = Color.Gray)
        Text(text = "Release Date: ${movie.release}", color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = movie.overview, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle watch movie */ }) {
            Text("Watch Movie")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}