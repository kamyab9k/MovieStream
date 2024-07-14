package com.example.registerpage.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.graphics.Color
import com.example.registerpage.R
import com.example.registerpage.ui.navigationCompose.Screen

@Composable
fun BottomNavBar(
    currentScreen: Screen,
    onTabSelected: (Screen) -> Unit
) {
    BottomNavigation(backgroundColor = Color.Black) {
        BottomNavigationItem(
            icon = { Icon(ImageVector.vectorResource(id = R.drawable.baseline_local_movies_24), contentDescription = null, tint = if (currentScreen == Screen.MovieList) Color.LightGray else Color.DarkGray) },
            label = { Text("Movies", color = if (currentScreen == Screen.MovieList) Color.LightGray else Color.DarkGray) },
            selected = currentScreen == Screen.MovieList,
            onClick = { onTabSelected(Screen.MovieList) }
        )
        BottomNavigationItem(
            icon = { Icon(ImageVector.vectorResource(id = R.drawable.baseline_account_circle_24), contentDescription = null, tint = if (currentScreen == Screen.UserProfile) Color.LightGray else Color.DarkGray) },
            label = { Text("Profile", color = if (currentScreen == Screen.UserProfile) Color.LightGray else Color.DarkGray) },
            selected = currentScreen == Screen.UserProfile,
            onClick = { onTabSelected(Screen.UserProfile) }
        )
    }
}
