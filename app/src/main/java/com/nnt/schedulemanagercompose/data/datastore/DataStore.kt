package com.nnt.schedulemanagercompose.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.nnt.schedulemanagercompose.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException

/**
 * Created by ThangNNT on 02/12/2023.
 */
interface DataStore {
    var isFirstOpenApp: Boolean
}

class DataStoreImpl : DataStore {
    private val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(name = "app_data")
    private val dataStore = App.appContext.dataStore

    private val isFirstOpenAppPref = booleanPreferencesKey("isFirstOpenAppPref")
    override var isFirstOpenApp: Boolean
        get() = get(isFirstOpenAppPref, false)
        set(value) {
            put(isFirstOpenAppPref, value)
        }


    private inline fun <reified T>get(key: Preferences.Key<T>, default: T): T = runBlocking(
        Dispatchers.Default
    ) {
        return@runBlocking dataStore.getValueFlow(key, default).first()
    }

    private inline fun <reified T>put(key: Preferences.Key<T>, value: T) = runBlocking(
        Dispatchers.Default
    ) {
        dataStore.edit {
            it[key] = value
        }
    }

    private inline fun <reified T>getObject(key: Preferences.Key<String>): T = runBlocking(
        Dispatchers.Default
    ) {
        val jsonString = dataStore.getValueFlow(key, "").first()
        return@runBlocking Gson().fromJson(jsonString, T::class.java)
    }

    private inline fun <reified T>putObject(key: Preferences.Key<String>, value: T) = runBlocking(
        Dispatchers.Default
    ) {
        val jsonString = Gson().toJson(value)
        dataStore.edit {
            it[key] = jsonString
        }
    }

    private fun <T> androidx.datastore.core.DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> {
        return this.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }
}
