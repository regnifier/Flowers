package com.example.flowers.presentation.fragments.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowers.R


class ImageHeaderAdapter : RecyclerView.Adapter<ImageHeaderAdapter.HeaderViewHolder>() {

    var imageUrl: String = ""

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(imageUrl: String) {
        this.imageUrl = imageUrl
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = itemView.findViewById(R.id.user_image)

        fun bind() {
            Glide.with(itemView.context)
                .load(imageUrl.toUri())
                .centerCrop()
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_image, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 1
    }
}