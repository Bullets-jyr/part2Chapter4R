package kr.co.bullets.part2chapter4r.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.bullets.part2chapter4r.databinding.ItemUserBinding
import kr.co.bullets.part2chapter4r.model.User

class UserAdapter(val onClick: (User) -> Unit) : ListAdapter<User, UserAdapter.UserViewHoler>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHoler {
        return UserViewHoler(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHoler, position: Int) {
        holder.bind(currentList[position])
    }

    inner class UserViewHoler(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.usernameTextView.text = item.username
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}