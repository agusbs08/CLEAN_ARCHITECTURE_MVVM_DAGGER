package com.coba.aja.clean_architecture_mvvm_dagger.ui.main

import com.coba.aja.clean_architecture_mvvm_dagger.ui.main.detail.DetailFragment
import dagger.android.ContributesAndroidInjector


abstract class MainFragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun provideDetailsFragment(): DetailFragment?
}