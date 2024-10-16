package com.oleg.ivanov.test3205team.domain.data


sealed class RepositoryNetworkResultModel {
    internal data class DataOK(val listGitHub: List<GitHubRepoModel>?) : RepositoryNetworkResultModel()
    internal data class DataError(val e: Exception) : RepositoryNetworkResultModel()
}
