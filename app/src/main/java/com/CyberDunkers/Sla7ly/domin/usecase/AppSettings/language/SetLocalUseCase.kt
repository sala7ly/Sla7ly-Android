package com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.language

import com.CyberDunkers.Sla7ly.data.models.AppLocal
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import javax.inject.Inject

class SetLocalUseCase @Inject constructor(
    private val localUserManager: SettingLocalDataSource,

    ) {

    suspend operator fun invoke(appLocal: AppLocal) {
        localUserManager.setLocal(appLocal)
    }
}