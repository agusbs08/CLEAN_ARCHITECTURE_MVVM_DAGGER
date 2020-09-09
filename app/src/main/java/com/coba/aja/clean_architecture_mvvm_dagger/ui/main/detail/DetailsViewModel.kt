package com.coba.aja.clean_architecture_mvvm_dagger.ui.main.detail


import android.os.Bundle
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


class DetailsViewModel
@Inject constructor(val repository: Repository) : ViewModel() {
    private var disposable: CompositeDisposable?
    private val selectedRepo = MutableLiveData<Repo>()

    init {
        disposable = CompositeDisposable()
    }

    fun setSelectedRepo(repo: Repo?) {
        selectedRepo.value = repo
    }

    fun getSelectedRepo() : LiveData<Repo> = selectedRepo

    fun restoreFromBundle(savedInstanceState: Bundle?) {
        if (selectedRepo.value == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details")!!)
            }
        }
    }

    fun saveToBundle(outState: Bundle) {
        if (selectedRepo.value != null) {
            outState.putStringArray(
                "repo_details", arrayOf(
                    selectedRepo.value!!.owner.login,
                    selectedRepo.value!!.name
                )
            )
        }
    }

    private fun loadRepo(repo_details: Array<String>) {
        disposable!!.add(
            repository.getRepo(repo_details[0], repo_details[1])
            !!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Repo?>() {

                    override fun onSuccess(t: Repo) {
                        selectedRepo.value = t
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}

