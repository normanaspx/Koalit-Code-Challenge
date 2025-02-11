package com.ferhatozcelik.codechallenge.data.local

import android.content.Context
import android.content.SharedPreferences

class UserSessionManager(private val context: Context) {

    // Nombre del archivo SharedPreferences
    private val PREF_NAME = "user_session"

    // Claves para almacenar los datos
    private val KEY_USERNAME = "username"
    private val KEY_TOKEN = "token"
    private val KEY_IS_LOGGED_IN = "is_logged_in"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Guardar los detalles de la sesión del usuario
    fun saveUserSession(username: String, token: String) {
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_TOKEN, token)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply() // Confirma los cambios de forma asincrónica
    }


    // Borrar la sesión del usuario (cerrar sesión)
    fun clearUserSession() {
        editor.remove(KEY_USERNAME)
        editor.remove(KEY_TOKEN)
        editor.remove(KEY_IS_LOGGED_IN)
        editor.apply() // Confirma los cambios de forma asincrónica
    }

    // Verificar si el usuario está logueado
    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}
