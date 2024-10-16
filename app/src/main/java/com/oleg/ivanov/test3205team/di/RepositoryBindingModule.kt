package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.domain.domain.repository.RepositoryData
import com.oleg.ivanov.data.repository.RepositoryDataImpl
import com.oleg.ivanov.data.repository.git_hub_repo.GitHubRepoClient
import com.oleg.ivanov.data.repository.git_hub_repo.GitHubRepoClientImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class RepositoryBindingModule {

    @Binds
    @ApplicationScope
    abstract fun gitHubRepoClient(impl: com.oleg.ivanov.data.repository.git_hub_repo.GitHubRepoClientImpl): com.oleg.ivanov.data.repository.git_hub_repo.GitHubRepoClient

    @Binds
    @ApplicationScope
    abstract fun repositoryData(impl: com.oleg.ivanov.data.repository.RepositoryDataImpl): RepositoryData

}