package com.example.flowers.domain.models.entity

data class UserEntity(
    val albumId: Int,
    val name: String,
    val thumbnailUrl: String,
    val url: String,
    val userId: Int,
    val posts: Int
)
