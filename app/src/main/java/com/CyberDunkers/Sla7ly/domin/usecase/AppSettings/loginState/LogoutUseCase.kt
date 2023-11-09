package com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState

import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val settingLocalDataSource: SettingLocalDataSource
) {
    suspend operator fun invoke(){
        settingLocalDataSource.logout()
    }
}