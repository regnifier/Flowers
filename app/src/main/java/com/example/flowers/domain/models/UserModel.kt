package com.example.flowers.domain.models

import com.example.flowers.domain.models.entity.UserEntity

data class UserModel(
    val albumId: Int,
    val name: String,
    val thumbnailUrl: String,
    val url: String,
    val userId: Int
) {
    fun toEntity(postCount: Int): UserEntity {
        return UserEntity(
            albumId = albumId,
            name = name,
            thumbnailUrl = thumbnailUrl,
            url = url,
            userId = userId,
            posts = postCount
        )
    }
}