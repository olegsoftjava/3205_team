package com.oleg.ivanov.test3205team.di

import android.content.Context
import com.oleg.ivanov.test3205team.domain.repository.RepositoryData
import com.oleg.ivanov.test3205team.domain.usecase.RepositoryUseCase
import com.oleg.ivanov.test3205team.repository.RepositoryDataImpl
import com.oleg.ivanov.test3205team.repository.database.AppRoomDatabase
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        RoomModule::class
    ]
)
class AppModule(private val contextApp: Context) {

    @ApplicationScope
    @Provides
    fun provideContext() = contextApp
}