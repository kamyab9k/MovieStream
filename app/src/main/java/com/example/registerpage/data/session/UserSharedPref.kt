package com.example.registerpage.data.session

import android.content.Context

class UserSharedPref(context: Context) {
    
    private val sharedPreferences =
        context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)

    private val isSignedUpKey = "is_signed_up"

    fun saveSignUpStatus(isSignedUp: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(isSignedUpKey, isSignedUp)
        editor.apply()
    }

    fun getSignUpStatus(): Boolean {
        return sharedPreferences.getBoolean(isSignedUpKey, false)
    }

    fun clearSession() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun saveUserInfo(name: String, lastName: String, idNumber: String, pickedDate: String) {
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("lastName", lastName)
        editor.putString("idNumber", idNumber)
        editor.putString("pickedDate", pickedDate)
        editor.apply()
    }

    fun getName(): String? {
        return sharedPreferences.getString("name", null)
    }

    fun getLastName(): String? {
        return sharedPreferences.getString("lastName", null)
    }

    fun getIdNumber(): String? {
        return sharedPreferences.getString("idNumber", null)
    }

    fun getPickedDate(): String? {
        return sharedPreferences.getString("pickedDate", null)
    }
}
