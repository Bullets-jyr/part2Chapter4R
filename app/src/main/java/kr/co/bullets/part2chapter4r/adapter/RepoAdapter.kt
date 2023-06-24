package kr.co.bullets.part2chapter4r.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.bullets.part2chapter4r.databinding.ItemRepoBinding
import kr.co.bullets.part2chapter4r.databinding.ItemUserBinding
import kr.co.bullets.part2chapter4r.model.Repo
import kr.co.bullets.part2chapter4r.model.User

class RepoAdapter :
    ListAdapter<Repo, RepoAdapter.RepoViewHoler>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHoler {
        return RepoViewHoler(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHoler, position: Int) {
        holder.bind(currentList[position])
    }

    inner class RepoViewHoler(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            binding.repoNameTextView.text = repo.name
            binding.descriptionTextView.text = repo.description
            binding.starCountTextView.text = repo.starCount.toString()
            binding.forkCountTextView.text = "${repo.forksCount}"
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}