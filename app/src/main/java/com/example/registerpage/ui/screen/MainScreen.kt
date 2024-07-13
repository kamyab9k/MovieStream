package com.example.registerpage.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registerpage.ui.BottomNavBar
import com.example.registerpage.ui.navigationCompose.Screen
import com.example.registerpage.ui.viewModel.RegisterViewModel

@Composable
fun MainScreen(registerViewModel: RegisterViewModel) {
    val navController = rememberNavController()
    val currentScreen = remember { mutableStateOf<Screen>(Screen.MovieList) }


    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentScreen = currentScreen.value,
                onTabSelected = { screen ->
                    currentScreen.value = screen
                    when(screen) {
                        is Screen.MovieList -> navController.navigate("movie_list")
                        is Screen.UserProfile -> navController.navigate("user_profile")
                        Screen.Register -> TODO()
                        Screen.UserInfo -> TODO()
                        Screen.MainScreen -> TODO()
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "movie_list",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("movie_list") { MovieList() }
            composable("User_Profile") { UserProfile(navController, registerViewModel) }
        }
    }
}
