package com.tofik.task.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tofik.task.util.SessionManagement
import java.util.regex.Pattern

class LoginViewModel @ViewModelInject constructor(
    private val sessionManagement: SessionManagement
) : ViewModel() {

    private val _showProgress = MutableLiveData<Boolean>()
    private val _navigateToMap = MutableLiveData<Boolean>()
    private val _errorMessage = MutableLiveData<String>()

    val errorMessage: LiveData<String>
        get() = _errorMessage

    val navigateToMap: LiveData<Boolean>
        get() = _navigateToMap

    val showProgress: LiveData<Boolean>
        get() = _showProgress

    fun doneNavigating() {
        _navigateToMap.value = null
    }

    fun doneShowingError() {
        _errorMessage.value = null
    }

    fun loginUser(email: String, password: String) {
        _showProgress.value = true

            if (email.equals("test@android.com")&& password.equals("123456")) {
                _showProgress.value = false
                _navigateToMap.value = true
                //sessionManagement.setLoggedIn()
                sessionManagement.createSession(true, email)
            } else {
                _errorMessage.value = "invalid login"
                _showProgress.value = false
            }

    }

//    suspend fun login(email : String , password: String) : AuthResult? {
//
//    }

    fun validateEmail(email: String): String {
        if (email.trim().isEmpty() || email.length == 0) {
            return "Please enter an email"
        }
        if (!Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
            ).matcher(email).matches()) {
            return "Please enter valid email"
        }




        return "ok"
    }

    fun validatePassword(password: String): String {
        if (password.trim().isEmpty() || password.length == 0) {
            return "Please enter a password"
        }
        if (password.length < 6) {
            return "Minimum password must be 7 chars"
        }
        return "ok"
    }
}