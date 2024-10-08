package com.oleg.ivanov.test3205team.repository.database

class DatabaseProvider(private val appDatabase: AppRoomDatabase) {
    fun downloadLinkAndFileAppDao() = appDatabase.downloadLinkAndFileAppDao
}