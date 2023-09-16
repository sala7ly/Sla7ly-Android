package com.CyberDunkers.Sla7ly.domin.usecase.loginState

import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import javax.inject.Inject

class SaveLoginState @Inject constructor(
   private val settingLocalDataSource: SettingLocalDataSource
) {
    suspend operator fun invoke(){
        settingLocalDataSource.saveLoginState()
    }

}