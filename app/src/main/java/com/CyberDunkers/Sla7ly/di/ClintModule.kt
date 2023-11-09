package com.CyberDunkers.Sla7ly.di

import com.CyberDunkers.Sla7ly.data.remote.ClintApiInterface
import com.CyberDunkers.Sla7ly.data.repository.ClintRepoImpl
import com.CyberDunkers.Sla7ly.domin.repository.ClintRepo
import com.CyberDunkers.Sla7ly.domin.usecase.clint.DataConfermation.DataConformationUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.AuthWithGoogleUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.ClintRegistrationUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.ClintVerifyUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.UpdateUserUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.jobPosting.CreateNewJobUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.jobPosting.GetJobPostCommentsUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.jobPosting.GetMyAllJobPostsUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.jobPosting.JobPostUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.clint.selectWorker.SelectWorkerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ClintModule {

    @Provides
    @Singleton
    fun provideClintRepo(clintApiInterface: ClintApiInterface): ClintRepo =
        ClintRepoImpl(clintApiInterface)

    @Provides
    @Singleton
    fun provideAuthWithGoogleUseCases(
        clintRepo: ClintRepo,
    ) = AuthWithGoogleUseCase(clintRepo)

    @Provides
    @Singleton
    fun provideClintRegistrationUseCases(
        clintRepo: ClintRepo,
    ) = ClintRegistrationUseCase(clintRepo)

    @Provides
    @Singleton
    fun provideClintVerifyUseCases(
        clintRepo: ClintRepo,
    ) = ClintVerifyUseCase(clintRepo)

    @Provides
    @Singleton
    fun provideGetUserCodeUseCases(
        clintRepo: ClintRepo,
    ) = UpdateUserUseCase(clintRepo)

    @Provides
    @Singleton
    fun providesJobPostUseCases(
        clintRepo: ClintRepo,
    ) = JobPostUseCases(
        createNewJobUseCase = CreateNewJobUseCase(clintRepo),
        getJobPostCommentsUseCase = GetJobPostCommentsUseCase(clintRepo),
        getMyAllJobPostsUseCase = GetMyAllJobPostsUseCase(clintRepo)
    )

    @Provides
    @Singleton
    fun provideDataConformationUseCases(
        clintRepo: ClintRepo,
    ) = DataConformationUseCase(clintRepo)

    @Provides
    @Singleton
    fun provideSelectWorkerUseCases(
        clintRepo: ClintRepo,
    ) = SelectWorkerUseCase(clintRepo)







}