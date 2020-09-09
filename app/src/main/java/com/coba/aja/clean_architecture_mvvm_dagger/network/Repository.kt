package com.coba.aja.clean_architecture_mvvm_dagger.network

import com.coba.aja.clean_architecture_mvvm_dagger.model.Repo
import io.reactivex.Single
import javax.inject.Inject

class Repository
@Inject constructor(val restAPI: RestAPI) {

    fun getRepositories() : Single<List<Repo?>?>? = restAPI.getRepositories()
    fun getRepo(owner : String, name : String) = restAPI.getRepo(owner, name)

}