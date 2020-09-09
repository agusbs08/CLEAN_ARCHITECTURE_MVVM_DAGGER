package com.coba.aja.clean_architecture_mvvm_dagger.di.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.coba.aja.clean_architecture_mvvm_dagger.base.BaseFragment
import com.coba.aja.clean_architecture_mvvm_dagger.di.util.ViewModelFactory
import javax.inject.Inject

abstract class BindingFragment<B : ViewDataBinding, VM : ViewModel> : BaseFragment<B>() {

    @Inject
    private lateinit var viewModelFactory : ViewModelFactory
    lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}