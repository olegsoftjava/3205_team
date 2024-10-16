package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.data.di.ApplicationScope
import com.oleg.ivanov.data.repository.provider.ProviderDownloader
import dagger.Module
import dagger.Provides

@Module
class ProviderModule {

    @ApplicationScope
    @Provides
    fun providerDownloader() = ProviderDownloader()
}