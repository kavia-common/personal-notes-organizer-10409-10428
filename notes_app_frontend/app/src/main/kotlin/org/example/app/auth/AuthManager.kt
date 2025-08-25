package org.example.app.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * PUBLIC_INTERFACE
 * Simple local authentication manager storing a pseudo token in EncryptedSharedPreferences.
 * Not for production use; serves as demo auth for offline app.
 */
class AuthManager(context: Context) {

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "auth-prefs",
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // PUBLIC_INTERFACE
    fun isLoggedIn(): Boolean = prefs.getString(KEY_TOKEN, null) != null

    // PUBLIC_INTERFACE
    fun login(email: String, password: String): Boolean {
        /** Accepts any non-empty email/password; stores a simple token. */
        return if (email.isNotBlank() && password.isNotBlank()) {
            prefs.edit().putString(KEY_TOKEN, "token_${email}").apply()
            true
        } else false
    }

    // PUBLIC_INTERFACE
    fun logout() {
        /** Remove the token. */
        prefs.edit().remove(KEY_TOKEN).apply()
    }

    companion object {
        private const val KEY_TOKEN = "auth_token"
    }
}
