package com.oleg.ivanov.test3205team.repository.git_hub_repo

import com.oleg.ivanov.test3205team.repository.model.GitHubRepoModel

interface GitHubRepoClient {
    suspend fun getUserRepositories(userName: String): List<GitHubRepoModel>?
}