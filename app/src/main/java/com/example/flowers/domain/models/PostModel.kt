package com.example.flowers.domain.models

data class PostModel(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)