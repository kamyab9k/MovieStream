package com.example.movieStream.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.movieStream.data.repository.Movie
import com.example.movieStream.data.repository.Series

@Composable
fun SeriesItem(series: Series, onClick: (Series) -> Unit) {
    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    val painter = rememberImagePainter(
        data = IMAGE_BASE_URL + series.poster,
        builder = {
            crossfade(true)
        }
    )

    Column(
        modifier = Modifier
            .clickable { onClick(series) }
            .padding(8.dp) // Adjust padding if necessary
    ) {
        Image(
            painter = painter,
            contentDescription = series.name, // Add contentDescription for accessibility
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(180.dp) // Adjust size if necessary
                .height(270.dp) // Adjust size if necessary
                .clip(RoundedCornerShape(8.dp)) // Adjust corner radius if necessary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = series.name ?: "Unknown Title", // Provide default text if series.title is null
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "(${series.first_air_date.take(4) ?: "Unknown Year"})", // Safeguard for null values
            color = Color.Gray,
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold,
            )
        )
    }
}