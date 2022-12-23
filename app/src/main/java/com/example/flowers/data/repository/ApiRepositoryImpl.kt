package com.example.flowers.data.repository

import com.example.flowers.data.network.FlowersAPI
import com.example.flowers.domain.models.PostModel
import com.example.flowers.domain.models.UserModel
import com.example.flowers.domain.repository.ApiRepository

class ApiRepositoryImpl(private val flowersAPI: FlowersAPI) : ApiRepository {

    override suspend fun getPosts(): List<PostModel> {
        return flowersAPI.getPosts()
    }

    override suspend fun getUsers(): List<UserModel> {
        return flowersAPI.getUsers()
    }

}