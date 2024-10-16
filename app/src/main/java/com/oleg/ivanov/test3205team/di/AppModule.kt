package com.oleg.ivanov.test3205team.di

import android.content.Context
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