package com.example.angeladoctor.ui.clean.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataStore @Inject constructor(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "doctor")

    private object AppPreferencesKeys {
        val UserName = stringPreferencesKey("UserName")
    }

    fun getUserName() = context.applicationContext.dataStore.data.map { preferences ->
        preferences[AppPreferencesKeys.UserName] ?: ""
    }

    suspend fun setUserName(name: String) {
        context.applicationContext.dataStore.edit { preferences ->
            preferences[AppPreferencesKeys.UserName] = name
        }
    }

}