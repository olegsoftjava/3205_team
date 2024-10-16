package com.oleg.ivanov.test3205team.di

import android.content.Context
import com.oleg.ivanov.test3205team.repository.database.AppRoomDatabase
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import dagger.Module
import dagger.Provides

@Module(
    includes = []
)
class RoomModule(private val context: Context) {

    @ApplicationScope
    @Provides
    fun appDatabase() = AppRoomDatabase.getDataBase(context)

    @ApplicationScope
    @Provides
    fun databaseProvider() = DatabaseProvider(appDatabase())

}