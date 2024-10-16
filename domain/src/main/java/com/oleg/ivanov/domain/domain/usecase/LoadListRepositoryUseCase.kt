package com.oleg.ivanov.domain.domain.usecase

import com.oleg.ivanov.domain.domain.data.RepositoryNetworkAndLoadedResultModel
import com.oleg.ivanov.domain.domain.data.RepositoryNetworkResultModel
import com.oleg.ivanov.domain.domain.repository.RepositoryData

class LoadListRepositoryUseCase(
    private val repositoryData: RepositoryData,
) {
    suspend fun execute(userName: String): RepositoryNetworkAndLoadedResultModel {

        return when (val resultSearch = repositoryData.getDataByUserName(userName)) {
            is RepositoryNetworkResultModel.DataOK -> {
                val loadedLinks = repositoryData.getDataByUserNameFromCache(userName)
                RepositoryNetworkAndLoadedResultModel.DataOK(resultSearch.listGitHub, loadedLinks)
            }

            is RepositoryNetworkResultModel.DataError -> {
                RepositoryNetworkAndLoadedResultModel.DataError(resultSearch.e)
            }
        }
    }
}