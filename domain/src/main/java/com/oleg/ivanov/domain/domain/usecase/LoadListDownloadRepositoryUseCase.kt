package com.oleg.ivanov.domain.domain.usecase

import com.oleg.ivanov.domain.domain.repository.RepositoryData

class LoadListDownloadRepositoryUseCase(private val repositoryData: RepositoryData) {
    suspend fun execute() = repositoryData.getDataFromCache()
}