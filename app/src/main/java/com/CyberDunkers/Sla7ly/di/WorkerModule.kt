package com.CyberDunkers.Sla7ly.di

import com.CyberDunkers.Sla7ly.data.remote.WorkerApiInterface
import com.CyberDunkers.Sla7ly.data.repository.WorkerRepoImp
import com.CyberDunkers.Sla7ly.domin.repository.WorkerRepo
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.WorkerAuthWithGoogleUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.WorkerRegistrationUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.WorkerVerifyUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.worker.auth.UpdateWorkerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {

    @Provides
    @Singleton
    fun provideWorkerRepo(workerApiInterface: WorkerApiInterface): WorkerRepo =
        WorkerRepoImp(workerApiInterface)

    @Provides
    @Singleton
    fun provideWorkerAuthWithGoogleUseCases(
        workerRepo: WorkerRepo,
    ) = WorkerAuthWithGoogleUseCase(workerRepo)

    @Provides
    @Singleton
    fun provideWorkerRegistrationUseCases(
        workerRepo: WorkerRepo,
    ) = WorkerRegistrationUseCase(workerRepo)

    @Provides
    @Singleton
    fun provideWorkerVerifyUseCases(
        workerRepo: WorkerRepo,
    ) = WorkerVerifyUseCase(workerRepo)

    @Provides
    @Singleton
    fun provideUpdateWorkerUseCases(
        workerRepo: WorkerRepo,
    ) = UpdateWorkerUseCase(workerRepo)


}