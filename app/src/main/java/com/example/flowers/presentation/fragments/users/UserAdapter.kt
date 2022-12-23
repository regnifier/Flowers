package com.example.flowers.presentation.fragments.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowers.R
import com.example.flowers.databinding.ItemUserBinding
import com.example.flowers.domain.models.entity.UserEntity


class UserAdapter(
    private val userClick: (userId: Int) -> Unit
) : ListAdapter<UserEntity, UserAdapter.UserViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: UserEntity) = with(binding) {
            itemView.setOnClickListener {
                userClick.invoke(item.userId)
            }
            Glide.with(root.context)
                .load(item.url.toUri())
                .into(userLogo)
            userName.text = item.name
            userPosts.text =
                item.posts.toString() + " " + binding.root.context.getString(R.string.posts)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(old: UserEntity, new: UserEntity): Boolean =
            old.userId == new.userId

        override fun areContentsTheSame(
            old: UserEntity,
            new: UserEntity
        ): Boolean =
            old.userId == new.userId
    }
}