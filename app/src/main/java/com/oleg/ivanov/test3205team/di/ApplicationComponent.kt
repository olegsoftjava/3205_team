package com.oleg.ivanov.test3205team.di

import com.oleg.ivanov.data.di.ApplicationScope
import com.oleg.ivanov.data.di.RetrofitModule
import com.oleg.ivanov.data.di.RoomModule
import com.oleg.ivanov.test3205team.presentation.list_repository_screen.ListDownloadRepositoryBindingActivity
import com.oleg.ivanov.test3205team.presentation.search_screen.SearchActivity
import com.oleg.ivanov.test3205team.util.FileDownloader
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        RepositoryBindingModule::class,
        RetrofitModule::class,
        ViewModelModule::class,
        ProviderModule::class,
        UseCaseModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(searchActivity: SearchActivity)
    fun inject(fileDownloader: FileDownloader)
    fun inject(listDownloadRepositoryBindingActivity: ListDownloadRepositoryBindingActivity)

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder
        fun roomModule(roomModule: RoomModule): Builder
        fun build(): ApplicationComponent
    }
}
