package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.data.repository.provider.ProviderDownloader
import dagger.Module
import dagger.Provides

@Module
class ProviderModule {

    @ApplicationScope
    @Provides
    fun providerDownloader(): com.oleg.ivanov.data.repository.provider.ProviderDownloader {
        return com.oleg.ivanov.data.repository.provider.ProviderDownloader()
    }
}