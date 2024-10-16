package com.oleg.ivanov.test3205team.repository.git_hub_repo

import com.oleg.ivanov.test3205team.domain.data.RepositoryNetworkResultModel

interface GitHubRepoClient {
    suspend fun getUserRepositories(userName: String): RepositoryNetworkResultModel
}