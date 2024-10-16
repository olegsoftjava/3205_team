package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.test3205team.domain.repository.RepositoryData
import com.oleg.ivanov.test3205team.repository.RepositoryDataImpl
import com.oleg.ivanov.test3205team.repository.git_hub_repo.GitHubRepoClient
import com.oleg.ivanov.test3205team.repository.git_hub_repo.GitHubRepoClientImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class RepositoryBindingModule {

    @Binds
    @ApplicationScope
    abstract fun gitHubRepoClient(impl: GitHubRepoClientImpl): GitHubRepoClient

    @Binds
    @ApplicationScope
    abstract fun repositoryData(impl: RepositoryDataImpl): RepositoryData

}