package com.example.registerpage.ui.navigationCompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.registerpage.ui.screen.MovieList
import com.example.registerpage.ui.screen.RegisterScreen
import com.example.registerpage.ui.screen.UserInfoScreen
import com.example.registerpage.ui.viewModel.RegisterViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, registerViewModel: RegisterViewModel) {

    NavHost(
        navController = navController,

        startDestination =
        if (registerViewModel.signUpStatus) {
            Screen.MovieList.route
        } else {
            Screen.Register.route
        }
    ) {
        composable(
            route = Screen.Register.route
        ) {
            RegisterScreen(navController, registerViewModel)
        }

        composable(
            route = Screen.UserInfo.route,
        ) {
            UserInfoScreen(navController, registerViewModel)
        }
        composable(
            route = Screen.MovieList.route
        ) {
            MovieList()
        }
    }
}