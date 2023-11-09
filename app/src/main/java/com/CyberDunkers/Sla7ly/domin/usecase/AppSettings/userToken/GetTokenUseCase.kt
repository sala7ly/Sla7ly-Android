package com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.userToken

import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
   private val settingLocalDataSource: SettingLocalDataSource
){
     fun getToken() : Flow<String> = settingLocalDataSource.getUserToken()
     fun getUserId() : Flow<Int> = settingLocalDataSource.getUserId()

}