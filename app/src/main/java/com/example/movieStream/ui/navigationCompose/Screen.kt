package com.example.movieStream.ui.navigationCompose


sealed class Screen(val route: String) {
    data object Register :
        Screen(route = "register_screen")
    data object UserInfo :
        Screen(route = "userInfo_screen" + "/{name_key}" + "/{lastName_key}" + "/{id_key}" + "/{pickedDate_key}")
    data object MovieList : Screen(route = "movie_list")
    data object UserProfile : Screen(route = "User_Profile")
    data object MainScreen : Screen(route = "Main_Screen")

}