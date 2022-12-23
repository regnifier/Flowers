package com.example.flowers.presentation.fragments.users

import androidx.lifecycle.viewModelScope
import com.example.flowers.domain.models.entity.UserEntity
import com.example.flowers.domain.repository.ApiRepository
import com.example.flowers.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val apiRepository: ApiRepository) : BaseViewModel() {

    private val _userModelList = MutableStateFlow(listOf<UserEntity>())
    val userList get() = _userModelList.asStateFlow()

    private val _errorListener = MutableStateFlow(false)
    val errorListener get() = _errorListener.asStateFlow()

    private val errorHandler by lazy {
        CoroutineExceptionHandler { _, _ -> _errorListener.value = true }
    }

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch(errorHandler) {
            val userList = apiRepository.getUsers()
            val postList = apiRepository.getPosts()
            val userEntityList = mutableListOf<UserEntity>()
            userList.forEach { userModel ->
                userEntityList.add(userModel.toEntity(postList.filter { it.userId == userModel.userId }.size))
            }
            _userModelList.tryEmit(userEntityList)
        }
    }
}