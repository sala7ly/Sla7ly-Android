package com.CyberDunkers.Sla7ly.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.data.models.AppLocal
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context,
) : SettingLocalDataSource {

    object PreferenceKeys {
        val APP_ENTRY = booleanPreferencesKey(Constants.PREFERENCE_NAME)
        val appLocal = stringPreferencesKey("app_local")
        val userData = stringPreferencesKey("user_data")
        val LOGIN_STATE = booleanPreferencesKey(Constants.LOGIN_STATE_PREF)
        val user_id = intPreferencesKey("userId")

    }


    // app Entry
    override suspend fun saveAppEntry() {
        context.dataStore.edit {
            it[PreferenceKeys.APP_ENTRY] = true
        }
    }


    override fun getAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferenceKeys.APP_ENTRY] ?: false
        }
    }



    override suspend fun saveLoginState() {
            context.dataStore.edit {
                it[PreferenceKeys.LOGIN_STATE] = true
            }
    }

    override suspend fun logout() {
        context.dataStore.edit {
            it[PreferenceKeys.LOGIN_STATE] = false
        }
    }

    override fun getLoginState(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferenceKeys.LOGIN_STATE] ?: false
        }
    }

    override suspend fun getLocal(): Flow<AppLocal> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.appLocal]?.toAppLocal() ?: AppLocal.EN
        }
    }

    // language
    override suspend fun setLocal(appLocal: AppLocal) {
        context.dataStore.edit {
            it[PreferenceKeys.appLocal] = appLocal.toStringLocal()
        }
    }


    override fun getUserToken(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.userData] ?: "empty"
        }
    }

    override suspend fun setUserToken(token: String) {
        context.dataStore.edit {
            it[PreferenceKeys.userData] = token
        }
    }

    override suspend fun delToken() {
        context.dataStore.edit {
            it[PreferenceKeys.userData] = ""
        }
    }

    override fun getUserId(): Flow<Int> {
        return context.dataStore.data.map {
            it[PreferenceKeys.user_id] ?: -1
        }
    }

    override suspend fun setUserId(id: Int) {
         context.dataStore.edit {
            it[PreferenceKeys.user_id] = id
        }
    }


}


private val Context.dataStore by preferencesDataStore(
    name = Constants.USER_SETTINGS
)


private fun String.toAppLocal(): AppLocal {
    return when (this) {
        AppLocal.EN.name -> AppLocal.EN
        AppLocal.AR.name -> AppLocal.AR
        else -> AppLocal.EN
    }
}

private fun AppLocal.toStringLocal(): String {
    return this.name
}