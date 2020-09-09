package com.coba.aja.clean_architecture_mvvm_dagger.ui.main.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.coba.aja.clean_architecture_mvvm_dagger.R
import com.coba.aja.clean_architecture_mvvm_dagger.databinding.FragmentListListBinding
import com.coba.aja.clean_architecture_mvvm_dagger.model.Repo

import com.coba.aja.clean_architecture_mvvm_dagger.ui.main.list.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class RepoListRecyclerViewAdapter(
    val viewModel: ListViewModel, val lifecycleOwner: LifecycleOwner, val repoSelectedListener: RepoSelectedListener
) : RecyclerView.Adapter<RepoListRecyclerViewAdapter.ViewHolder>() {

    private val repos : MutableList<Repo?> = mutableListOf()

    init {
        viewModel.getRepos().observe(lifecycleOwner, Observer { t -> {
            repos.clear()
            if(t != null) {
                repos.addAll(t)
                notifyDataSetChanged()
            }
        } })
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_list_list, parent, false)
        return ViewHolder(view, repoSelectedListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int = repos.size

    inner class ViewHolder(view: View, repoSelectedListener: RepoSelectedListener) : RecyclerView.ViewHolder(view) {
        private val binding : FragmentListListBinding? = DataBindingUtil.bind<FragmentListListBinding>(view)
        private var repo: Repo? = null

        init {
            itemView.setOnClickListener { v -> {
                if (repo != null) {
                    repoSelectedListener.onRepoSelectedListener(repo)
                }
            } }
        }

        fun bind(repo: Repo?) {
            this.repo = repo
            binding!!.tvRepoName.setText(repo?.name)
            binding.tvRepoDescription.setText(repo?.description)
            binding.tvForks.setText(repo?.forks_count.toString())
            binding.tvStars.setText(repo?.stars.toString())
        }
    }
}