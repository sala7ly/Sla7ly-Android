package com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase

import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppEntryUseCase @Inject constructor(
    private val localUserManager: SettingLocalDataSource,
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.getAppEntry()
    }

}