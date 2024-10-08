package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.test3205team.app.AppSettings
import com.oleg.ivanov.test3205team.network.GitHubApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule {

    @ApplicationScope
    @Provides
    fun retrofit(
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(AppSettings.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @ApplicationScope
    @Provides
    fun okHttpClient(
        //loggingBodyInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            //.addInterceptor(loggingBodyInterceptor)
            .build()
    }

    @ApplicationScope
    @Provides
    fun networkService(retrofit: Retrofit): GitHubApiService =
        retrofit.create(GitHubApiService::class.java)

}