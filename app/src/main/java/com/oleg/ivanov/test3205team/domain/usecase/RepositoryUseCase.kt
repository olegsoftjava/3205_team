package com.oleg.ivanov.test3205team.domain.usecase

import com.oleg.ivanov.test3205team.app.AppSettings
import com.oleg.ivanov.test3205team.domain.data.RepositoryNetworkAndLoadedResultModel
import com.oleg.ivanov.test3205team.domain.data.RepositoryNetworkResultModel
import com.oleg.ivanov.test3205team.domain.repository.RepositoryData
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryUseCase(
    private val repositoryData: RepositoryData,
    private val databaseProvider: DatabaseProvider
) {
    suspend fun execute(userName: String): RepositoryNetworkAndLoadedResultModel {

        return when (val resultSearch = repositoryData.getDataByUserName(userName)) {
            is RepositoryNetworkResultModel.DataOK -> {
                val loadedLinks = withContext(Dispatchers.IO) {
                    databaseProvider.downloadLinkAndFileAppDao().getFileByDownloadUserName(userName)
                        ?.map {
                            it.link?.replace(AppSettings.PART_URL_FOR_DOWNLOAD, "") ?: ""
                        }
                }
                RepositoryNetworkAndLoadedResultModel.DataOK(resultSearch.listGitHub, loadedLinks)
            }

            is RepositoryNetworkResultModel.DataError -> {
                RepositoryNetworkAndLoadedResultModel.DataError(resultSearch.e)
            }
        }
    }
}