package com.example.registerpage.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .fillMaxSize()
            .background(Color(0xFF2E2E2E)) // Dark grey background
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Section with Profile Picture and Greeting
        HeaderSection(registerViewModel = registerViewModel)

        Spacer(modifier = Modifier.height(64.dp))

        // User Info Section
        UserInfoSection(registerViewModel = registerViewModel)

        Spacer(modifier = Modifier.height(16.dp))

        // Logout Button
        Button(
            onClick = {
                registerViewModel.clearSession()
                navController.popBackStack()
                navController.navigate(route = Screen.Register.route)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFBF0000), // Modern red color
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.button_logout_info),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HeaderSection(registerViewModel: RegisterViewModel) {
    val userInfo = registerViewModel.getUserInfo()
    val profilePic: Painter = painterResource(id = R.drawable.ic_profile_placeholder) // Replace with your profile pic resource

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = profilePic,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(bottom = 12.dp)
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .border(2.dp, Color.White, CircleShape)
        )
        Text(
            text = "Hello",
            color = Color.Gray,
            fontSize = 12.sp
        )
        Text(
            text = userInfo.name ?: "",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun UserInfoSection(registerViewModel: RegisterViewModel) {
    val userInfo = registerViewModel.getUserInfo()
    Column(modifier = Modifier.fillMaxWidth()) {
        userInfo.lastName?.let {
            UserInfoItem(label = stringResource(id = R.string.lastname_info), value = it)
        }
        userInfo.idNumber?.let {
            UserInfoItem(label = stringResource(id = R.string.id_number_info), value = it)
        }
        userInfo.pickedDate?.let {
            UserInfoItem(label = stringResource(id = R.string.Birthday_info), value = it)
        }
    }
}

@Composable
fun UserInfoItem(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(16.dp))
            .background(Color(0xFF2E2E2E)) // Match background to user profile screen
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = value,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}