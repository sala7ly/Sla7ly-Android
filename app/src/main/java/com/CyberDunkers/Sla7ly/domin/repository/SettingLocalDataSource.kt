package com.CyberDunkers.Sla7ly.domin.repository

import com.CyberDunkers.Sla7ly.data.models.AppLocal
import kotlinx.coroutines.flow.Flow

interface SettingLocalDataSource {

    suspend fun saveAppEntry()
    fun getAppEntry(): Flow<Boolean>

    suspend fun getLocal(): Flow<AppLocal>
    suspend fun setLocal(appLocal: AppLocal)

    suspend fun saveLoginState()
    suspend fun logout()
    fun getLoginState(): Flow<Boolean>


     fun getUserToken(): Flow<String>
     suspend fun setUserToken(token: String)
     suspend fun delToken()


    fun getUserId(): Flow<Int>
    suspend fun setUserId(id: Int)
}