package com.oleg.ivanov.test3205team.domain.repository

import com.oleg.ivanov.test3205team.domain.data.RepositoryNetworkResultModel

interface RepositoryData {
    suspend fun getDataByUserName(userName: String): RepositoryNetworkResultModel
}