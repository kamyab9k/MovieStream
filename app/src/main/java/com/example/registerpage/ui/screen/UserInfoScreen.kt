package com.example.registerpage.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.registerpage.R
import com.example.registerpage.ui.navigationCompose.Screen
import com.example.registerpage.ui.viewModel.RegisterViewModel

@Composable
fun UserInfoScreen(
    navController: NavHostController,
    registerViewModel: RegisterViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = stringResource(
                id = R.string.user_info_title
            ),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
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
                        stringResource(
                            id = R.string.name_info
                        ), it
                    )
                }
            }
            item {
                CustomDivider()
            }

            item {
                registerViewModel.getUserInfo().lastName?.let {
                    DataItem(
                        stringResource(
                            id = R.string.lastname_info
                        ), it
                    )
                }
            }
            item {
                CustomDivider()
            }
            item {
                registerViewModel.getUserInfo().idNumber?.let {
                    DataItem(
                        stringResource(
                            id = R.string.id_number_info
                        ), it
                    )
                }
            }
            item {
                CustomDivider()
            }
            item {
                registerViewModel.getUserInfo().pickedDate?.let {
                    DataItem(
                        stringResource(
                            id = R.string.Birthday_info
                        ), it
                    )
                }
            }
            item {
                CustomDivider()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Button(
            onClick = {
                registerViewModel.clearSession()
                navController.popBackStack()
                navController.navigate(route = Screen.Register.route)
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                stringResource(
                    id = R.string.button_logout_info
                ), modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun DataItem(fieldName: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = fieldName, fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}

@Composable
fun CustomDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )
}