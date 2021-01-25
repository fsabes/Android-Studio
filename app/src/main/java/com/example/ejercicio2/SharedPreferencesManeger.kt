package com.example.ejercicio2

import android.content.Context
import com.example.ejercicio2.entities.Cliente
import com.google.gson.Gson

object SharedPreferencesManeger {
    private const val PREFERENCES_NAME = "MyPreferences"
    private const val USER_KEY = "Cliente"
    private val gson = Gson()

    private fun getPreferences(context: Context) = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveUser(context: Context, cliente: Cliente) {
        getPreferences(context)
        .edit()
        .putString(USER_KEY, gson.toJson(cliente))
        .apply()
    }

    fun listar(context: Context) : String? {
        val preferences = getPreferences(context)
        return preferences.getString(USER_KEY,"")
    }

    fun getUser(context: Context, cliente: Cliente): Cliente? {
        val userInJson = getPreferences(context)
        .getString(USER_KEY, "")
        return gson.fromJson(userInJson, Cliente::class.java)
    }

    fun clearData(context: Context) {
        getPreferences(context).edit().clear().apply()
    }
}