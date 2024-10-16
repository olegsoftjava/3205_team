package com.oleg.ivanov.data.repository.git_hub_repo

import com.oleg.ivanov.data.network.GitHubApiService
import com.oleg.ivanov.domain.domain.data.GitHubRepoModelDomain
import com.oleg.ivanov.domain.domain.data.OwnerModel
import com.oleg.ivanov.domain.domain.data.RepositoryNetworkResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubRepoClientImpl @Inject constructor(private val gitHubApiService: GitHubApiService) :
    GitHubRepoClient {
    override suspend fun getUserRepositories(userName: String): RepositoryNetworkResultModel {
        return withContext(Dispatchers.IO) {
            try {
                RepositoryNetworkResultModel.DataOK(
                    gitHubApiService.getUserRepositories(userName).map {
                        GitHubRepoModelDomain(
                            nameRepository = it.nameRepository,
                            htmlUrl = it.htmlUrl,
                            description = it.description,
                            owner = OwnerModel(
                                it.owner.userName
                            )
                        )
                    })
            } catch (e: Exception) {
                RepositoryNetworkResultModel.DataError(e)
            }
        }
    }
}