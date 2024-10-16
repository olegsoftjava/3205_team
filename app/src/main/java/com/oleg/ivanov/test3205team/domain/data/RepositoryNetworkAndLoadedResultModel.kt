package com.oleg.ivanov.test3205team.domain.data


sealed class RepositoryNetworkAndLoadedResultModel {
    internal data class DataOK(val listGitHub: List<GitHubRepoModel>?, val listLoaded: List<String>?) : RepositoryNetworkAndLoadedResultModel()
    internal data class DataError(val e: Exception) : RepositoryNetworkAndLoadedResultModel()
}
