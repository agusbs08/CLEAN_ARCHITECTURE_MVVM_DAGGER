package com.coba.aja.clean_architecture_mvvm_dagger.di.module

import com.coba.aja.clean_architecture_mvvm_dagger.ui.main.MainActivity
import com.coba.aja.clean_architecture_mvvm_dagger.ui.main.MainFragmentBindingModule

import dagger.android.ContributesAndroidInjector




abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity?
}