package com.oleg.ivanov.domain.domain.data


sealed class RepositoryNetworkResultModel {
    data class DataOK(val listGitHub: List<GitHubRepoModelDomain>?) : RepositoryNetworkResultModel()
    data class DataError(val e: Exception) : RepositoryNetworkResultModel()
}
