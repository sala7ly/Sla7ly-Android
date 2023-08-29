package com.CyberDunkers.Sla7ly.di

import android.app.Application
import com.CyberDunkers.Sla7ly.data.repository.LocalUserManagerImpl
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.AppEntryUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.GetAppEntryUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.SaveAppEntryUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.language.GetLocalUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.language.LocalUserCases
import com.CyberDunkers.Sla7ly.domin.usecase.language.SetLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): SettingLocalDataSource =
        LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideLocalUserManagerUseCases(
        localUserManager: SettingLocalDataSource,
    ) = AppEntryUseCases(
        getAppEntryUseCase = GetAppEntryUseCase(localUserManager),
        saveAppEntryUseCase = SaveAppEntryUseCase(localUserManager)

    )

    @Provides
    @Singleton
    fun provideLanguageUseCases(
        localUserManager: SettingLocalDataSource,
    ) = LocalUserCases(
        getLocalUseCase = GetLocalUseCase(localUserManager) ,
        setLocalUseCase = SetLocalUseCase(localUserManager)
    )

}