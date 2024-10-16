package com.oleg.ivanov.data.repository.database

class DatabaseProvider(private val appDatabase: AppRoomDatabase) {
    fun downloadLinkAndFileAppDao() = appDatabase.downloadLinkAndFileAppDao
}