package com.coba.aja.clean_architecture_mvvm_dagger.di.binding

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.coba.aja.clean_architecture_mvvm_dagger.base.BaseActivity
import com.coba.aja.clean_architecture_mvvm_dagger.di.util.ViewModelFactory
import javax.inject.Inject


abstract class BindingActivity<B : ViewDataBinding, VM : ViewModel> : BaseActivity<B>(){
    @Inject
    private lateinit var viewModelFactory : ViewModelFactory
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModel::class.java)
    }
}