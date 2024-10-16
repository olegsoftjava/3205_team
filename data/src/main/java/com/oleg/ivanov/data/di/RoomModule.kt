package com.oleg.ivanov.data.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module(
    includes = []
)
class RoomModule(private val context: Context) {

    @ApplicationScope
    @Provides
    fun appDatabase() = com.oleg.ivanov.data.repository.database.AppRoomDatabase.getDataBase(context)

    @ApplicationScope
    @Provides
    fun databaseProvider() =
        com.oleg.ivanov.data.repository.database.DatabaseProvider(appDatabase())

}