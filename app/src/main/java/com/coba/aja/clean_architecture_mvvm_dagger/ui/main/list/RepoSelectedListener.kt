package com.coba.aja.clean_architecture_mvvm_dagger.ui.main.list

import com.coba.aja.clean_architecture_mvvm_dagger.model.Repo

interface RepoSelectedListener {
    fun onRepoSelectedListener(repo: Repo?)
}