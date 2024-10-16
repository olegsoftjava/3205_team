package com.oleg.ivanov.domain.domain.usecase

import com.oleg.ivanov.domain.domain.data.DownloadLinkAndFileDomain
import com.oleg.ivanov.domain.domain.repository.RepositoryData

class LoadListDownloadRepositoryUseCase(private val repositoryData: RepositoryData) {
    suspend fun execute(): List<DownloadLinkAndFileDomain>? {
        return repositoryData.getDataFromCache()
    }
}