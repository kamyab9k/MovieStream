package com.example.registerpage.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.registerpage.data.repository.Movie

@Composable
fun MovieItem(movie: Movie, onClick: (Movie) -> Unit) {
    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    val painter = rememberImagePainter(
        data = IMAGE_BASE_URL + movie.poster,
        builder = {
            crossfade(true)
        }
    )

    Row(
        modifier = Modifier
            .clickable { onClick(movie) }
            .padding(4.dp) // Adjust padding if necessary
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(180.dp) // Adjust size if necessary
                .height(270.dp) // Adjust size if necessary
                .clip(RoundedCornerShape(8.dp)) // Adjust corner radius if necessary
        )
    }}