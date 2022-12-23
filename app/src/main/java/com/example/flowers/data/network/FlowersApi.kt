package com.example.flowers.data.network

import com.example.flowers.domain.models.PostModel
import com.example.flowers.domain.models.UserModel
import com.example.flowers.utils.NetworkUrls
import retrofit2.http.GET

interface FlowersAPI {

    @GET(NetworkUrls.GET_POSTS)
    suspend fun getPosts(): List<PostModel>

    @GET(NetworkUrls.GET_USERS)
    suspend fun getUsers(): List<UserModel>

}