package com.oleg.ivanov.test3205team.domain.repository

import com.oleg.ivanov.test3205team.domain.data.GitHubRepoModel

interface RepositoryData {
    suspend fun getDataByUserName(userName: String): List<GitHubRepoModel>?
}