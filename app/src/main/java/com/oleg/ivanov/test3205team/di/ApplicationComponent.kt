package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.test3205team.presentation.list_repository_screen.ListDownloadRepositoryBindingActivity
import com.oleg.ivanov.test3205team.presentation.search_screen.SearchActivity
import com.oleg.ivanov.test3205team.util.FileDownloader
import dagger.Component
import javax.inject.Scope

@Scope
@Retention
annotation class ApplicationScope

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        RepositoryBindingModule::class,
        RetrofitModule::class,
        ViewModelModule::class,
        ProviderModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(searchActivity: SearchActivity)
    fun inject(fileDownloader: FileDownloader)
    fun inject(listDownloadRepositoryBindingActivity: ListDownloadRepositoryBindingActivity)

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder
        fun build(): ApplicationComponent
    }
}
