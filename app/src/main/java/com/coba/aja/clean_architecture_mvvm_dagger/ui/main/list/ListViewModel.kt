package com.coba.aja.clean_architecture_mvvm_dagger.ui.main.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coba.aja.clean_architecture_mvvm_dagger.model.Repo
import com.coba.aja.clean_architecture_mvvm_dagger.network.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel
    @Inject constructor(val repository: Repository): ViewModel() {

    private val disposable : CompositeDisposable?
    private val repos : MutableLiveData<List<Repo?>>
    private val repoLoadError : MutableLiveData<Boolean>
    private val loading : MutableLiveData<Boolean>

    init {
        disposable = CompositeDisposable()
        repos = MutableLiveData()
        repoLoadError = MutableLiveData()
        loading = MutableLiveData()
        fetchRepos()
    }

    fun getRepos() : LiveData<List<Repo?>?> = repos

    fun getError() : LiveData<Boolean> = repoLoadError

    fun getLoading() : LiveData<Boolean> = loading

    fun fetchRepos() {
        loading.value = true
        disposable!!.add(
            repository.getRepositories()
                !!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Repo?>?>() {
                    override fun onSuccess(t: List<Repo?>) {
                        repos.value = t
                        repoLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        repoLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }
}