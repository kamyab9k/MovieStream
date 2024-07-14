package com.example.movieStream.data.repository

import android.content.Context
import com.example.movieStream.data.model.UserInformation
import com.example.movieStream.data.session.UserSharedPref

class UserRepository(context: Context) {

    private val userSharedPref = UserSharedPref(context)

    fun saveSignUpStatus(isSignedUp: Boolean) {
        userSharedPref.saveSignUpStatus(isSignedUp)
    }

    fun getSignUpStatus(): Boolean {
        return userSharedPref.getSignUpStatus()
    }

    fun clearSession() {
        userSharedPref.clearSession()
    }

    fun saveUserData(userData: UserInformation) {
        userSharedPref.saveUserInfo(
            userData.name ?: "",
            userData.lastName ?: "",
            userData.idNumber ?: "",
            userData.pickedDate ?: ""
        )
    }

    fun getUserData(): UserInformation {
        return UserInformation(
            userSharedPref.getName(),
            userSharedPref.getLastName(),
            userSharedPref.getIdNumber(),
            userSharedPref.getPickedDate()
        )
    }
}