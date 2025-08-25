package org.example.app.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import org.example.app.auth.AuthManager

/**
 * PUBLIC_INTERFACE
 * AuthViewModel wraps the AuthManager state and actions.
 */
class AuthViewModel(app: Application) : AndroidViewModel(app) {
    private val auth = AuthManager(app)

    val isAuthenticated = MutableLiveData<Boolean>(auth.isLoggedIn())

    // PUBLIC_INTERFACE
    fun login(email: String, password: String) {
        if (auth.login(email, password)) {
            isAuthenticated.value = true
        }
    }

    // PUBLIC_INTERFACE
    fun logout() {
        auth.logout()
        isAuthenticated.value = false
    }
}
