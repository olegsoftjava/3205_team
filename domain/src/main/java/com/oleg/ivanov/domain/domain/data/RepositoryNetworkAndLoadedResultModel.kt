package com.oleg.ivanov.domain.domain.data


sealed class RepositoryNetworkAndLoadedResultModel {
    data class DataOK(val listGitHub: List<GitHubRepoModel>?, val listLoaded: List<String>?) : RepositoryNetworkAndLoadedResultModel()
    data class DataError(val e: Exception) : RepositoryNetworkAndLoadedResultModel()
}
