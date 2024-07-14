package com.example.movieStream.ui.screen

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
import com.example.movieStream.R
import com.example.movieStream.ui.viewModel.RegisterViewModel

@Composable
fun UserProfile(
    navController: NavHostController,
    registerViewModel: RegisterViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E2E2E)) // Dark grey background
            .padding(16.dp) // Reduce padding
    ) {
        // Header Section with Profile Picture and Greeting
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp), // Add padding for content inside the Box
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection(registerViewModel = registerViewModel)
            Spacer(modifier = Modifier.height(64.dp))
            // User Info Section
            UserInfoSection(registerViewModel = registerViewModel)
        }

        // Delete Account Button at the top right corner
//        Button(
//            onClick = {
//                registerViewModel.clearSession()
//                navController.popBackStack()
//                navController.navigate(route = Screen.Register.route)
//            },
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color(0xFFBF0000), // Modern red color
//                contentColor = Color.White
//            ),
//            modifier = Modifier
//                .align(Alignment.BottomEnd) // Align button to the top right corner
//                .padding(8.dp) // Padding to avoid touching the edges
//                .height(36.dp) // Smaller height
//        ) {
//            Text(
//                text = "Delete Account", // Text for the delete account button
//                fontSize = 10.sp, // Smaller font size
//                fontWeight = FontWeight.Bold
//            )
//        }
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Distribute space evenly
        ) {
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f) // Ensure the label takes its space
            )
            Spacer(modifier = Modifier.width(8.dp)) // Add some space between label and value
            Text(
                text = value,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f) // Ensure the value takes its space
            )
        }
    }
}