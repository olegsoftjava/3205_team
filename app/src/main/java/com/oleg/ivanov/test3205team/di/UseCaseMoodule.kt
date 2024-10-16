package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.test3205team.domain.repository.RepositoryData
import com.oleg.ivanov.test3205team.domain.usecase.LoadListDownloadRepositoryUseCase
import com.oleg.ivanov.test3205team.domain.usecase.LoadListRepositoryUseCase
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideLoadListRepositoryUseCase(repositoryData: RepositoryData, databaseProvider: DatabaseProvider): LoadListRepositoryUseCase {
        return LoadListRepositoryUseCase(repositoryData, databaseProvider)
    }

    @Provides
    fun provideLoadListDownloadRepositoryUseCase(databaseProvider: DatabaseProvider): LoadListDownloadRepositoryUseCase {
        return LoadListDownloadRepositoryUseCase(databaseProvider)
    }
}