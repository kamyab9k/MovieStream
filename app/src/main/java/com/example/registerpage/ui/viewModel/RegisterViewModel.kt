package com.example.registerpage.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.registerpage.data.model.UserInformation
import com.example.registerpage.data.repository.UserRepository

class RegisterViewModel(context: Context) : ViewModel() {

    private val userRepository = UserRepository(context)
    val signUpStatus = userRepository.getSignUpStatus()

    fun getUserInfo(): UserInformation {
        return userRepository.getUserData()
    }

    fun saveSignUpStatus(isSignedUp: Boolean) {
        userRepository.saveSignUpStatus(isSignedUp)
    }

    fun clearSession() {
        userRepository.clearSession()
    }

    fun saveUserData(name: String, lastName: String, idNumber: String, pickedDate: String) {
        val newUser = UserInformation(name, lastName, idNumber, pickedDate)
        userRepository.saveUserData(newUser)
    }
}
