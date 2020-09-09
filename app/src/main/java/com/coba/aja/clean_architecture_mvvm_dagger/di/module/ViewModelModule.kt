package com.coba.aja.clean_architecture_mvvm_dagger.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coba.aja.clean_architecture_mvvm_dagger.di.key.ViewModelKey
import com.coba.aja.clean_architecture_mvvm_dagger.di.util.ViewModelFactory
import com.coba.aja.clean_architecture_mvvm_dagger.ui.main.detail.DetailsViewModel
import com.coba.aja.clean_architecture_mvvm_dagger.ui.main.list.ListViewModel
import dagger.Binds
import dagger.multibindings.IntoMap


abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(detailsViewModel: DetailsViewModel?): ViewModel?

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?
}