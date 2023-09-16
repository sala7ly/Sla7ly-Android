package com.CyberDunkers.Sla7ly.di

import android.app.Application
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.data.remote.ApiInterface
import com.CyberDunkers.Sla7ly.data.repository.AuthRepoImpl
import com.CyberDunkers.Sla7ly.data.repository.LocalUserManagerImpl
import com.CyberDunkers.Sla7ly.domin.repository.AuthRepo
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.AppEntryUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.GetAppEntryUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.SaveAppEntryUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.auth.AuthUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.auth.LoginUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.auth.RegisterUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.language.GetLocalUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.language.LocalUserCases
import com.CyberDunkers.Sla7ly.domin.usecase.language.SetLocalUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.loginState.GetLoginState
import com.CyberDunkers.Sla7ly.domin.usecase.loginState.LoginStateUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.loginState.LogoutUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.loginState.SaveLoginState
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
    fun provideApi(): ApiInterface {
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

        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(apiInterface: ApiInterface): AuthRepo =
        AuthRepoImpl(apiInterface)

    @Provides
    @Singleton
    fun provideAuthUseCases(
        authRepo: AuthRepo,
    ) = AuthUseCases(
        loginUseCase = LoginUseCase(authRepo),
        registerUseCase = RegisterUseCase(authRepo)
    )


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
    fun provideLanguageUseCases(
        localUserManager: SettingLocalDataSource,
    ) = LocalUserCases(
        getLocalUseCase = GetLocalUseCase(localUserManager),
        setLocalUseCase = SetLocalUseCase(localUserManager)
    )

}