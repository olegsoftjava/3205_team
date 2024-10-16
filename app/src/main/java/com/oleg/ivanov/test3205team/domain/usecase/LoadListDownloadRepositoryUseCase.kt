package com.oleg.ivanov.test3205team.domain.usecase

import com.oleg.ivanov.test3205team.domain.data.DownloadLinkAndFile
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadListDownloadRepositoryUseCase(private val databaseProvider: DatabaseProvider) {
    suspend fun execute(): List<DownloadLinkAndFile>? {
        return withContext(Dispatchers.IO) {
            databaseProvider.downloadLinkAndFileAppDao().getAll()
        }
    }
}