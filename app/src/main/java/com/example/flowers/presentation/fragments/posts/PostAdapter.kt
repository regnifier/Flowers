package com.example.flowers.presentation.fragments.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers.databinding.ItemPostBinding
import com.example.flowers.domain.models.PostModel


class PostAdapter(
) : ListAdapter<PostModel, PostAdapter.PostHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(
        ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: PostModel) = with(binding) {
            postTitle.text = item.title
            postBody.text =
                item.body
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PostModel>() {
        override fun areItemsTheSame(old: PostModel, new: PostModel): Boolean =
            old.userId == new.userId

        override fun areContentsTheSame(
            old: PostModel,
            new: PostModel
        ): Boolean =
            old.userId == new.userId
    }
}