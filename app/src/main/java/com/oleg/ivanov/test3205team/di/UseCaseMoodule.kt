package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.domain.domain.repository.RepositoryData
import com.oleg.ivanov.domain.domain.usecase.LoadListDownloadRepositoryUseCase
import com.oleg.ivanov.domain.domain.usecase.LoadListRepositoryUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideLoadListRepositoryUseCase(repositoryData: RepositoryData) =
        LoadListRepositoryUseCase(repositoryData)

    @Provides
    fun provideLoadListDownloadRepositoryUseCase(repositoryData: RepositoryData) =
        LoadListDownloadRepositoryUseCase(repositoryData)
}
