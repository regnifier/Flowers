package com.example.flowers.domain.repository

import com.example.flowers.domain.models.PostModel
import com.example.flowers.domain.models.UserModel

interface ApiRepository {

    suspend fun getPosts(): List<PostModel>

    suspend fun getUsers(): List<UserModel>

}