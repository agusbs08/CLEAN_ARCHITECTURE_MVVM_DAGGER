package com.coba.aja.clean_architecture_mvvm_dagger.ui.main

import android.os.Bundle
import com.coba.aja.clean_architecture_mvvm_dagger.R
import com.coba.aja.clean_architecture_mvvm_dagger.base.BaseActivity
import com.coba.aja.clean_architecture_mvvm_dagger.databinding.ActivityMainBinding
import com.coba.aja.clean_architecture_mvvm_dagger.ui.main.list.ListFragment


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.screenContainer, ListFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun layoutRes(): Int = R.layout.activity_main
}