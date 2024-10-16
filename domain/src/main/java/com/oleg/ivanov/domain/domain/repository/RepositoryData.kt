package com.oleg.ivanov.domain.domain.repository

import com.oleg.ivanov.domain.domain.data.DownloadLinkAndFileDomain
import com.oleg.ivanov.domain.domain.data.RepositoryNetworkResultModel

interface RepositoryData {
    suspend fun getDataByUserName(userName: String): RepositoryNetworkResultModel
    suspend fun getDataFromCache(): List<DownloadLinkAndFileDomain>?
    suspend fun getDataByUserNameFromCache(userName: String): List<String>?
}