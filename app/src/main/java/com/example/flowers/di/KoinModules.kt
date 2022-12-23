package com.example.flowers.di

import com.example.flowers.data.repository.ApiRepositoryImpl
import com.example.flowers.domain.repository.ApiRepository
import com.example.flowers.presentation.fragments.posts.PostViewModel
import com.example.flowers.presentation.fragments.users.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get()) }
}

val viewModelsModule = module {
    viewModel { UserViewModel(apiRepository = get()) }
    viewModel { PostViewModel(apiRepository = get()) }
}