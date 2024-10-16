package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.domain.domain.repository.RepositoryData
import com.oleg.ivanov.domain.domain.usecase.LoadListDownloadRepositoryUseCase
import com.oleg.ivanov.domain.domain.usecase.LoadListRepositoryUseCase
import com.oleg.ivanov.data.repository.database.DatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideLoadListRepositoryUseCase(repositoryData: com.oleg.ivanov.domain.domain.repository.RepositoryData, databaseProvider: com.oleg.ivanov.data.repository.database.DatabaseProvider): com.oleg.ivanov.domain.domain.usecase.LoadListRepositoryUseCase {
        return com.oleg.ivanov.domain.domain.usecase.LoadListRepositoryUseCase(
            repositoryData,
            databaseProvider
        )
    }

    @Provides
    fun provideLoadListDownloadRepositoryUseCase(databaseProvider: com.oleg.ivanov.data.repository.database.DatabaseProvider): com.oleg.ivanov.domain.domain.usecase.LoadListDownloadRepositoryUseCase {
        return com.oleg.ivanov.domain.domain.usecase.LoadListDownloadRepositoryUseCase(
            databaseProvider
        )
    }
}