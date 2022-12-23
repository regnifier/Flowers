package com.example.flowers.presentation.fragments.posts

import androidx.lifecycle.viewModelScope
import com.example.flowers.domain.models.PostModel
import com.example.flowers.domain.models.UserModel
import com.example.flowers.domain.repository.ApiRepository
import com.example.flowers.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val apiRepository: ApiRepository) : BaseViewModel() {

    private val _postModelList = MutableStateFlow(listOf<PostModel>())
    val postList get() = _postModelList.asStateFlow()

    private val _headerImage = MutableStateFlow("")
    val headerImage get() = _headerImage.asStateFlow()

    private val _errorListener = MutableStateFlow(false)
    val errorListener get() = _errorListener.asStateFlow()

    private val errorHandler by lazy {
        CoroutineExceptionHandler { _, _ -> _errorListener.value = true }
    }

    fun postHeaderImage(userId: Int) {
        viewModelScope.launch(errorHandler) {
            val userList = apiRepository.getUsers()
            val user: UserModel = userList.single { it.userId == userId }
            _headerImage.tryEmit(user.url)
        }
    }

    fun getUserData(userId: Int) {
        viewModelScope.launch(errorHandler) {
            val postList = apiRepository.getPosts()
            _postModelList.tryEmit(postList.filter { it.userId == userId })
        }
    }
}