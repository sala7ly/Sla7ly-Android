package com.CyberDunkers.Sla7ly.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.data.models.applocal.AppLocal
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context,
) : SettingLocalDataSource {

    object PreferenceKeys {
        val APP_ENTRY = booleanPreferencesKey(Constants.PREFERENCE_NAME)
        val appLocal = stringPreferencesKey("app_local")

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



    // language
    override suspend fun setLocal(appLocal: AppLocal) {
        context.dataStore.edit {
            it[PreferenceKeys.appLocal] = appLocal.toStringLocal()
        }
    }

    override suspend fun getLocal(): Flow<AppLocal> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.appLocal]?.toAppLocal() ?: AppLocal.EN
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