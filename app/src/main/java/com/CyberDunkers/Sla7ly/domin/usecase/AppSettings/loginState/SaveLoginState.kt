package com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState

import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import javax.inject.Inject

class SaveLoginState @Inject constructor(
   private val settingLocalDataSource: SettingLocalDataSource
) {
    suspend operator fun invoke(response : AuthVerifyResponse) {
        settingLocalDataSource.saveLoginState()
        settingLocalDataSource.setUserId(response.id)
        settingLocalDataSource.setUserToken(response.token)
    }

}