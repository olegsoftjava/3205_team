package com.oleg.ivanov.test3205team.di

import android.content.Context
import com.oleg.ivanov.data.di.ApplicationScope
import com.oleg.ivanov.data.di.RoomModule
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