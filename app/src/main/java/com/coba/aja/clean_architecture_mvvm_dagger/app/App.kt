package com.coba.aja.clean_architecture_mvvm_dagger.app

import com.coba.aja.clean_architecture_mvvm_dagger.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }

}