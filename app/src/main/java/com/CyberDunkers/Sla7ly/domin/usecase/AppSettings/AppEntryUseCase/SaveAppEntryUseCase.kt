package com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.AppEntryUseCase

import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import javax.inject.Inject

class SaveAppEntryUseCase @Inject constructor(
    private val localUserManager: SettingLocalDataSource
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}