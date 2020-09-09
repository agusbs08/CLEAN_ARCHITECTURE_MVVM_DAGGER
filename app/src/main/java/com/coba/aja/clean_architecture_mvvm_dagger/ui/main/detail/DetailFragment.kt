package com.coba.aja.clean_architecture_mvvm_dagger.ui.main.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.coba.aja.clean_architecture_mvvm_dagger.R
import com.coba.aja.clean_architecture_mvvm_dagger.databinding.FragmentDetailBinding
import com.coba.aja.clean_architecture_mvvm_dagger.di.binding.BindingFragment
import com.coba.aja.clean_architecture_mvvm_dagger.model.Repo
import java.lang.String

/**
 * A fragment representing a list of Items.
 */
class DetailFragment(): BindingFragment<FragmentDetailBinding, DetailsViewModel>() {

    constructor(repo: Repo) : this() {
        viewModel.setSelectedRepo(repo)
    }

    override fun layoutRes(): Int = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.restoreFromBundle(savedInstanceState)
        displayRepo()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveToBundle(outState)
    }

    private fun displayRepo() {
        viewModel.getSelectedRepo().observe(viewLifecycleOwner, Observer { t ->  {
            binding.tvRepoName.setText(t.name)
            binding.tvRepoDescription.setText(t.description)
            binding.tvForks.setText(t.forks_count.toString())
            binding.tvForks.setText(t.forks_count.toString())
        }})
    }

}
