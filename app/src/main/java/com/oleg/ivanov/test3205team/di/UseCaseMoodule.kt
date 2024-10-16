package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.test3205team.domain.repository.RepositoryData
import com.oleg.ivanov.test3205team.domain.usecase.RepositoryUseCase
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideRepositoryUseCase(repositoryData: RepositoryData, databaseProvider: DatabaseProvider): RepositoryUseCase {
        return RepositoryUseCase(repositoryData, databaseProvider)
    }
}