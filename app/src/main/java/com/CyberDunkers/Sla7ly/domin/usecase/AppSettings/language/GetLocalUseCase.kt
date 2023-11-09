package com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.language

import com.CyberDunkers.Sla7ly.data.models.AppLocal
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUseCase @Inject constructor(
    private val localUserManager: SettingLocalDataSource,
) {

    suspend operator fun invoke(): Flow<AppLocal> = localUserManager.getLocal()

}