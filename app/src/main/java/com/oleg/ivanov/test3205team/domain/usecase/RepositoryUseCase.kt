package com.oleg.ivanov.test3205team.domain.usecase

import com.oleg.ivanov.test3205team.domain.repository.RepositoryData
import com.oleg.ivanov.test3205team.domain.data.GitHubRepoModel

class RepositoryUseCase(private val repositoryData: RepositoryData) {
    suspend fun execute(userName: String): List<GitHubRepoModel>? {
        return repositoryData.getDataByUserName(userName)
    }
}