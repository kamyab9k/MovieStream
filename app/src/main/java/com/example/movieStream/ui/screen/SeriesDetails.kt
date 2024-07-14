package com.example.movieStream.ui.screen

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.movieStream.data.repository.Movie
import com.example.movieStream.data.repository.Series

@Composable
fun SeriesDetails(series: Series, onBack: () -> Unit) {
    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    val scrollState = rememberScrollState()

    // State to control the zoom animation
    var imageScale by remember { mutableStateOf(0.8f) }
    var imageAlpha by remember { mutableStateOf(0.5f) }

    // Animate scaling and alpha values
    val scaleAnimation by animateFloatAsState(
        targetValue = if (imageScale == 1f) 1f else 0.8f,
        animationSpec = tween(durationMillis = 900)
    )
    val alphaAnimation by animateFloatAsState(
        targetValue = if (imageAlpha == 1f) 1f else 0.5f,
        animationSpec = tween(durationMillis = 900)
    )

    // Start animation after the composable is first placed
    LaunchedEffect(Unit) {
        imageScale = 1f
        imageAlpha = 1f
    }

    // Get the current context for Toast
    val context = LocalContext.current

    // Dark grey background for the entire screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D2D2D)) // Dark grey background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp) // Set a fixed height for the image box
                    .clip(RoundedCornerShape(16.dp)) // Apply rounded corners to all sides
            ) {
                Image(
                    painter = rememberImagePainter(IMAGE_BASE_URL + series.poster),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)) // Apply rounded corners to all sides
                        .graphicsLayer(
                            scaleX = scaleAnimation,
                            scaleY = scaleAnimation,
                            alpha = alphaAnimation
                        ),
                    contentScale = ContentScale.Crop
                )

                // Apply the fade effect using a Box layered on top
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp) // Height of the fade effect
                        .align(Alignment.BottomCenter)
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 16.dp,
                                bottomStart = 16.dp
                            )
                        ) // Match the poster's rounded corners
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                ), // Less opaque
                                startY = 0f,
                                endY = 120f
                            )
                        )
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = series.name,
                            style = MaterialTheme.typography.h5.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(bottom = 4.dp),
                            maxLines = 2 // Allow title to wrap onto the next line
                        )
                        Text(
                            text = "(${series.first_air_date.take(4)})", // Display only the year
                            color = Color.Gray,
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                // Centered outlined play button
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(alpha = 0.4f)) // Light gray with transparency
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = CircleShape
                        ) // White border
                        .padding(8.dp) // Padding inside the button
                        .clickable {
                            // Show Toast when the play button is clicked
                            Toast
                                .makeText(
                                    context,
                                    "Buy subscription to watch the movie",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        } // Clickable area
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.White, // White color for the play icon
                        modifier = Modifier
                            .size(40.dp) // Size of the play icon
                            .align(Alignment.Center) // Center the icon
                    )
                }
            }

            // Divider line between poster and score
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Rating, vote count, and language
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Score: %.1f".format((series.vote_average.toDouble())),
                    color = Color.Gray,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Vote count
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${series.vote_count} votes",
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                // Language
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = series.original_language,
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            // Divider line between score and plot
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Plot overview with improved font style
            Text(
                text = series.overview,
                color = Color.White, // Change text color to white for contrast
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal, // Use normal font weight for better readability
                    fontSize = 14.sp // Adjust font size for better readability
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Back button
            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text("Back", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
