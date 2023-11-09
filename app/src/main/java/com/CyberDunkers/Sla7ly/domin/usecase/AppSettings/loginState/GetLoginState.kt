package com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState

import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoginState @Inject constructor(
   private val settingLocalDataSource: SettingLocalDataSource
){
    operator fun invoke() : Flow<Boolean> = settingLocalDataSource.getLoginState()
}