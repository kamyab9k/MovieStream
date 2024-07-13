package com.example.registerpage.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.registerpage.data.repository.Movie
@Composable
fun MovieItem(movie: Movie, onClick: (Movie) -> Unit) {
     val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(movie) }
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(IMAGE_BASE_URL + movie.poster),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = movie.title, color = Color.Black)
//            Text(text = "Score: ${movie.score}", color = Color.Gray)
            Text(text = "Release Date: ${movie.release}", color = Color.Gray)
        }
    }
}