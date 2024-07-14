package com.example.movieStream.ui.navigationCompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieStream.ui.screen.MainScreen
import com.example.movieStream.ui.screen.MovieList
import com.example.movieStream.ui.screen.RegisterScreen
import com.example.movieStream.ui.screen.UserProfile
import com.example.movieStream.ui.viewModel.RegisterViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, registerViewModel: RegisterViewModel) {

    NavHost(
        navController = navController,

        startDestination =
        if (registerViewModel.signUpStatus) {
            Screen.MainScreen.route
        } else {
            Screen.Register.route
        }
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(registerViewModel)
        }
        composable(
            route = Screen.Register.route
        ) {
            RegisterScreen(navController, registerViewModel)
        }

//        composable(
//            route = Screen.UserInfo.route,
//        ) {
//            UserInfoScreen(navController, registerViewModel)
//        }
        composable(
            route = Screen.MovieList.route
        ) {
            MovieList()
        }
        composable(
            route = Screen.UserProfile.route
        ) {
            UserProfile(navController,registerViewModel)
        }
    }
}