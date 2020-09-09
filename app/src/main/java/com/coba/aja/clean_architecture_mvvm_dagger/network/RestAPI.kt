package com.coba.aja.clean_architecture_mvvm_dagger.network

import com.coba.aja.clean_architecture_mvvm_dagger.model.Repo
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Path


interface RestAPI {
    @GET("orgs/Google/repos")
    fun getRepositories(): Single<List<Repo?>?>?

    @GET("repos/{owner}/{name}")
    fun getRepo(
        @Path("owner") owner: String?,
        @Path("name") name: String?
    ): Single<Repo?>?
}