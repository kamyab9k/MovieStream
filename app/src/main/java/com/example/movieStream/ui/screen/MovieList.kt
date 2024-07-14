package com.example.movieStream.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.movieStream.data.OkHttpClientInstance
import com.example.movieStream.data.repository.Movie
import com.example.movieStream.data.repository.Series
import com.example.movieStream.data.repository.parseMoviesResponse
import com.example.movieStream.data.repository.parseSeriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MovieList() {
    var movies by remember { mutableStateOf(listOf<Movie>()) }
    var series by remember { mutableStateOf(listOf<Series>()) }
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    var selectedSeries by remember { mutableStateOf<Series?>(null) }
    var loadingMovies by remember { mutableStateOf(true) }
    var loadingSeries by remember { mutableStateOf(true) }
    var errorMovies by remember { mutableStateOf<String?>(null) }
    var errorSeries by remember { mutableStateOf<String?>(null) }

    // State for LazyRow auto-scrolling
    val lazyRowState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()

    // State to control LazyRow visibility
    var showLazyRow by remember { mutableStateOf(true) }

    // Animation for LazyRow offset
    val lazyRowOffset by animateDpAsState(
        targetValue = if (showLazyRow) 0.dp else -300.dp, // Adjust the value based on the LazyRow height
        animationSpec = tween(durationMillis = 500) // Smooth transition duration
    )

    // Coroutine scope for launching coroutines
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val moviesResponse = OkHttpClientInstance.getPopularMovies()
            val seriesResponse = OkHttpClientInstance.getPopularSeries()
            withContext(Dispatchers.Main) {
                loadingMovies = false
                loadingSeries = false
                if (moviesResponse?.isSuccessful == true) {
                    movies = parseMoviesResponse(moviesResponse)
                } else {
                    errorMovies = "Failed to load movies. Response code: ${moviesResponse?.code}"
                }
                if (seriesResponse?.isSuccessful == true) {
                    series = parseSeriesResponse(seriesResponse)
                } else {
                    errorSeries = "Failed to load series. Response code: ${seriesResponse?.code}"
                }
            }
        }
    }

    // Automatically scroll LazyRow
    LaunchedEffect(lazyRowState) {
        while (true) {
            delay(2500) // Scroll every 2.5 seconds
            val currentIndex = lazyRowState.firstVisibleItemIndex
            val itemCount = series.size
            val nextIndex = (currentIndex + 1) % itemCount
            coroutineScope.launch {
                lazyRowState.animateScrollToItem(nextIndex)
            }
        }
    }

    // Update visibility of LazyRow based on vertical scroll
    LaunchedEffect(lazyGridState.firstVisibleItemIndex) {
        showLazyRow = lazyGridState.firstVisibleItemIndex == 0
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when {
            loadingMovies || loadingSeries -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMovies != null -> {
                Text(
                    text = errorMovies ?: "Unknown error",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            errorSeries != null -> {
                Text(
                    text = errorSeries ?: "Unknown error",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                if (selectedMovie != null) {
                    MovieDetail(movie = selectedMovie!!, onBack = { selectedMovie = null })
                } else if (selectedSeries != null) {
                    SeriesDetails(series = selectedSeries!!, onBack = { selectedSeries = null })
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Conditionally render LazyRow with smooth transition
                        AnimatedVisibility(
                            visible = showLazyRow,
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = lazyRowOffset) // Apply offset animation
                                .height(300.dp) // Set a fixed height
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // Header with arrows to suggest scrolling
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 32.dp)
                                ) {
                                    IconButton(onClick = {
                                        coroutineScope.launch {
                                            lazyRowState.animateScrollToItem(
                                                (lazyRowState.firstVisibleItemIndex - 1 + series.size) % series.size
                                            )
                                        }
                                    }) {
//                                        Icon(
//                                            imageVector = Icons.Default.ArrowBack,
//                                            contentDescription = "Scroll Back",
//                                            tint = Color.White
//                                        )
                                    }
                                    Text(
                                        text = "Popular Series",
                                        style = MaterialTheme.typography.h6,
                                        color = Color.White,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier
                                            .weight(1f)
                                    )
                                    IconButton(onClick = {
                                        coroutineScope.launch {
                                            lazyRowState.animateScrollToItem(
                                                (lazyRowState.firstVisibleItemIndex + 1) % series.size
                                            )
                                        }
                                    }) {
//                                        Icon(
//                                            imageVector = Icons.Default.ArrowForward,
//                                            contentDescription = "Scroll Forward",
//                                            tint = Color.White
//                                        )
                                    }
                                }
                                LazyRow(
                                    state = lazyRowState,
                                    modifier = Modifier
                                        .padding(bottom = 1.dp)
                                        .fillMaxWidth(),
                                    contentPadding = PaddingValues(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(series) { seriesItem ->
                                        SeriesItem(series = seriesItem, onClick = { selectedSeries = it })
                                    }
                                }
                            }
                        }

                        // Title for the movies list
                        Text(
                            text = "Popular Movies",
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(32.dp)
                        )

                        // Grid for movie posters
                        LazyVerticalGrid(
                            state = lazyGridState,
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(movies) { movie ->
                                MovieItem(movie = movie, onClick = { selectedMovie = it })
                            }
                        }
                    }
                }
            }
        }
    }
}