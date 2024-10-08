package com.oleg.ivanov.test3205team.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model.ListLoadedRepositoryViewModelImpl
import com.oleg.ivanov.test3205team.presentation.search_screen.view_model.SearchViewModelImpl
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModelImpl::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListLoadedRepositoryViewModelImpl::class)
    abstract fun bindListLoadedRepositoryViewModel(viewModel: ListLoadedRepositoryViewModelImpl): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
