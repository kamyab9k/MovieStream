package com.example.movieStream.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieStream.ui.theme.RegisterPageTheme

@Preview(showBackground = true)
@Composable
fun MovieListPreview() {
    RegisterPageTheme {
        MovieList()
    }
}