package com.example.registerpage.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.registerpage.R
import com.example.registerpage.ui.navigationCompose.Screen
import com.example.registerpage.ui.viewModel.RegisterViewModel

@Composable
fun UserProfile(
    navController: NavHostController,
    registerViewModel: RegisterViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Display user information
        UserInfo(registerViewModel = registerViewModel)

        Spacer(modifier = Modifier.height(16.dp))

        // Logout Button

        Button(
            onClick = {
                registerViewModel.clearSession()
                navController.popBackStack()
                navController.navigate(route = Screen.Register.route)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White), // Set button color
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.button_logout_info),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun UserInfo(registerViewModel: RegisterViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            CustomDivider()
        }
        item {
            registerViewModel.getUserInfo().name?.let {
                DataItem(
                    stringResource(id = R.string.name_info), it
                )
            }
        }
        item {
            CustomDivider()
        }
        item {
            registerViewModel.getUserInfo().lastName?.let {
                DataItem(
                    stringResource(id = R.string.lastname_info), it
                )
            }
        }
        item {
            CustomDivider()
        }
        item {
            registerViewModel.getUserInfo().idNumber?.let {
                DataItem(
                    stringResource(id = R.string.id_number_info), it
                )
            }
        }
        item {
            CustomDivider()
        }
        item {
            registerViewModel.getUserInfo().pickedDate?.let {
                DataItem(
                    stringResource(id = R.string.Birthday_info), it
                )
            }
        }
        item {
            CustomDivider()
        }

    }

}
