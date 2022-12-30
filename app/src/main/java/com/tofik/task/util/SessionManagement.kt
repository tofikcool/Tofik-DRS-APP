package com.tofik.task.util

import android.content.SharedPreferences

class SessionManagement(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
    ) {

    companion object {
        const val KEY_IS_LOGIN: String = "is_login"
        const val KEY_USER_EMAIL: String = "user_email"

    }
    fun createSession(status: Boolean, email: String) {
        editor.putBoolean(KEY_IS_LOGIN, status)
        editor.putString(KEY_USER_EMAIL, email)
        editor.commit()
    }

    //Don't forget to redirect the user to the login screen
    fun clearUser() {
        editor.clear()
        editor.commit()
    }


    fun getValue(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun setLoggedIn() {
        editor.putBoolean(KEY_IS_LOGIN, true)
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGIN, false)
    }
}