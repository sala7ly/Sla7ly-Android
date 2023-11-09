package com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.userToken

import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import javax.inject.Inject

class DelTokenUseCase @Inject constructor(
    private val settingLocalDataSource: SettingLocalDataSource
) {
    suspend operator fun invoke(){
        settingLocalDataSource.delToken()
    }
}