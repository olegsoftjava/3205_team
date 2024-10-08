package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.test3205team.repository.provider.ProviderDownloader
import dagger.Module
import dagger.Provides

@Module
class ProviderModule {

    @ApplicationScope
    @Provides
    fun providerDownloader(): ProviderDownloader {
        return ProviderDownloader()
    }
}