package com.CyberDunkers.Sla7ly.di

import android.app.Application
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.data.remote.ClintApiInterface
import com.CyberDunkers.Sla7ly.data.remote.WorkerApiInterface
import com.CyberDunkers.Sla7ly.data.repository.LocalUserManagerImpl
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.AppEntryUseCase.AppEntryUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.AppEntryUseCase.GetAppEntryUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.AppEntryUseCase.SaveAppEntryUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.language.GetLocalUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.language.LocalUserCases
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.language.SetLocalUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState.GetLoginState
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState.LoginStateUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState.LogoutUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState.SaveLoginState
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.userToken.DelTokenUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.userToken.GetTokenUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.userToken.TokenUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideClintApi(): ClintApiInterface {
        val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging).build()

            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        return retrofit.create(ClintApiInterface::class.java)
    }


    @Provides
    @Singleton
    fun provideWorkerApi(): WorkerApiInterface {
        val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging).build()

            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        return retrofit.create(WorkerApiInterface::class.java)
    }


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
    fun provideLoginStateUseCases(
        settingLocalDataSource: SettingLocalDataSource,
    ) = LoginStateUseCases(
        getLoginState = GetLoginState(settingLocalDataSource),
        saveLoginState = SaveLoginState(settingLocalDataSource),
        logoutUseCase = LogoutUseCase(settingLocalDataSource)
    )

    @Provides
    @Singleton
    fun provideTokenUseCases(
        settingLocalDataSource: SettingLocalDataSource,
    ) = TokenUseCases(
        getToken = GetTokenUseCase(settingLocalDataSource),
        delTokenUseCase = DelTokenUseCase(settingLocalDataSource)
    )

    @Provides
    @Singleton
    fun provideLanguageUseCases(
        localUserManager: SettingLocalDataSource,
    ) = LocalUserCases(
        getLocalUseCase = GetLocalUseCase(localUserManager),
        setLocalUseCase = SetLocalUseCase(localUserManager)
    )

}