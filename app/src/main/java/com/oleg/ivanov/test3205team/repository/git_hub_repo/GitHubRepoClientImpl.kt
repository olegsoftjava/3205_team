package com.oleg.ivanov.test3205team.repository.git_hub_repo

import com.oleg.ivanov.test3205team.network.GitHubApiService
import com.oleg.ivanov.test3205team.repository.model.GitHubRepoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubRepoClientImpl @Inject constructor(private val gitHubApiService: GitHubApiService) :
    GitHubRepoClient {
    override suspend fun getUserRepositories(userName: String): List<GitHubRepoModel>? {
        return withContext(Dispatchers.IO) {
            try {
                gitHubApiService.getUserRepositories(userName)
            } catch (_: Exception) {
                null
            }
        }
    }
}