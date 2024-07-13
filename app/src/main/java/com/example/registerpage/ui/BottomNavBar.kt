package com.example.registerpage.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
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
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(ImageVector.vectorResource(id = R.drawable.baseline_local_movies_24), contentDescription = null) },
            label = { Text("Movies") },
            selected = currentScreen == Screen.MovieList,
            onClick = { onTabSelected(Screen.MovieList) }
        )
        BottomNavigationItem(
            icon = { Icon(ImageVector.vectorResource(id = R.drawable.baseline_account_circle_24), contentDescription = null) },
            label = { Text("Profile") },
            selected = currentScreen == Screen.UserProfile,
            onClick = { onTabSelected(Screen.UserProfile) }
        )
    }
}
