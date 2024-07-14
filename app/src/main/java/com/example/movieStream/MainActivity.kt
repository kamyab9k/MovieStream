package com.example.movieStream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieStream.ui.navigationCompose.SetupNavGraph
import com.example.movieStream.ui.viewModel.RegisterViewModel

class MainActivity : ComponentActivity() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerViewModel = RegisterViewModel(this)

        setContent {
            AppUI(
                navController = rememberNavController(),
                registerViewModel = registerViewModel
            )
        }
    }
}

@Composable
fun AppUI(navController: NavHostController, registerViewModel: RegisterViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        SetupNavGraph(navController = navController, registerViewModel = registerViewModel)
    }
}