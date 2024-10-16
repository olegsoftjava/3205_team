package com.oleg.ivanov.test3205team.repository

import com.oleg.ivanov.test3205team.domain.data.RepositoryNetworkResultModel
import com.oleg.ivanov.test3205team.domain.repository.RepositoryData
import com.oleg.ivanov.test3205team.repository.git_hub_repo.GitHubRepoClient
import javax.inject.Inject

class RepositoryDataImpl @Inject constructor(
    private val gitHubRepoClient: GitHubRepoClient
) : RepositoryData {
    override suspend fun getDataByUserName(userName: String): RepositoryNetworkResultModel {
        return gitHubRepoClient.getUserRepositories(userName)
    }
}