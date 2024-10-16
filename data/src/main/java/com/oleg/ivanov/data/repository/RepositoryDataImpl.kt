package com.oleg.ivanov.data.repository

import com.oleg.ivanov.data.repository.database.DatabaseProvider
import com.oleg.ivanov.data.repository.git_hub_repo.GitHubRepoClient
import com.oleg.ivanov.domain.domain.data.DownloadLinkAndFileDomain
import com.oleg.ivanov.domain.domain.data.RepositoryNetworkResultModel
import com.oleg.ivanov.domain.domain.repository.RepositoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryDataImpl @Inject constructor(
    private val gitHubRepoClient: GitHubRepoClient,
    private val databaseProvider: DatabaseProvider,
) : RepositoryData {
    override suspend fun getDataByUserName(userName: String): RepositoryNetworkResultModel {
        return gitHubRepoClient.getUserRepositories(userName)
    }

    override suspend fun getDataFromCache(): List<DownloadLinkAndFileDomain>? {
        return withContext(Dispatchers.IO) {
            databaseProvider.downloadLinkAndFileAppDao().getAll()?.map {
                DownloadLinkAndFileDomain(
                    id = it.id,
                    link = it.link,
                    file = it.file,
                    error = it.error,
                    downloadId = it.downloadId,
                    userName = it.userName,
                    repositoryName = it.repositoryName,
                    comleted = it.comleted,
                )
            }
        }
    }

    override suspend fun getDataByUserNameFromCache(userName: String): List<String>? {
        return withContext(Dispatchers.IO) {
            databaseProvider.downloadLinkAndFileAppDao().getFileByDownloadUserName(userName)
                ?.map {
                    it.link?.replace("/archive/refs/heads/main.zip", "") ?: ""
                }
        }
    }
}