package com.oleg.ivanov.test3205team.repository.git_hub_repo

import com.oleg.ivanov.test3205team.network.GitHubApiService
import com.oleg.ivanov.test3205team.domain.data.RepositoryNetworkResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubRepoClientImpl @Inject constructor(private val gitHubApiService: GitHubApiService) :
    GitHubRepoClient {
    override suspend fun getUserRepositories(userName: String): RepositoryNetworkResultModel {
        return withContext(Dispatchers.IO) {
            try {
                RepositoryNetworkResultModel.DataOK(gitHubApiService.getUserRepositories(userName))
            } catch (e: Exception) {
                RepositoryNetworkResultModel.DataError(e)
            }
        }
    }
}