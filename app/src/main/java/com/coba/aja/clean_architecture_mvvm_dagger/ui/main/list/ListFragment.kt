package com.coba.aja.clean_architecture_mvvm_dagger.ui.main.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.coba.aja.clean_architecture_mvvm_dagger.R
import com.coba.aja.clean_architecture_mvvm_dagger.databinding.FragmentListBinding
import com.coba.aja.clean_architecture_mvvm_dagger.di.binding.BindingFragment
import com.coba.aja.clean_architecture_mvvm_dagger.model.Repo
import com.coba.aja.clean_architecture_mvvm_dagger.ui.main.detail.DetailFragment

/**
 * A fragment representing a list of Items.
 */
class ListFragment : BindingFragment<FragmentListBinding, ListViewModel>(), RepoSelectedListener {
    override fun layoutRes(): Int = R.layout.fragment_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = RepoListRecyclerViewAdapter(viewModel, this, this)

        observableViewModel()
    }

    override fun onRepoSelectedListener(repo: Repo?) {
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.screenContainer, DetailFragment())
            .addToBackStack(null)
            .commit()
    }

    fun observableViewModel() {

        viewModel.getRepos().observe(viewLifecycleOwner, Observer { t -> {
            if(t != null) {
                binding.recyclerView.visibility = View.VISIBLE
            }
        } })

        viewModel.getError().observe(viewLifecycleOwner, Observer { t -> {
            if(t != null && t == true) {
                binding.tvError.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.tvError.setText("An Error Occurred While Loading Data")
            } else {
                binding.tvError.visibility = View.GONE
            }
        } })

        viewModel.getLoading().observe(viewLifecycleOwner, Observer { t -> {
            if(t != null && t == true) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        } })
    }
}