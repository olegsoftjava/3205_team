package com.oleg.ivanov.data.repository.git_hub_repo

import com.oleg.ivanov.domain.domain.data.RepositoryNetworkResultModel

interface GitHubRepoClient {
    suspend fun getUserRepositories(userName: String): com.oleg.ivanov.domain.domain.data.RepositoryNetworkResultModel
}