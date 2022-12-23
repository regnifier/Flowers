package com.example.flowers.presentation.fragments.posts

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.example.flowers.databinding.FragmentPostBinding
import com.example.flowers.presentation.base.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.getViewModel


class PostFragment :
    BaseFragment<FragmentPostBinding, PostViewModel>() {

    private val postAdapter by lazy { PostAdapter() }
    private val headerAdapter by lazy { ImageHeaderAdapter() }

    override val viewModel: PostViewModel
        get() = getViewModel()

    override fun getViewBinding(): FragmentPostBinding =
        FragmentPostBinding.inflate(layoutInflater)


    override fun initView() {
        observeArguments()
        initRecycler()
    }

    private fun initRecycler() = with(binding) {
        recyclerView.adapter = ConcatAdapter(headerAdapter, postAdapter)
    }

    private fun observeArguments() = with(viewModel) {
        postHeaderImage(arguments?.getInt(USER_ID) ?: 1)
        getUserData(arguments?.getInt(USER_ID) ?: 1)
    }

    override fun initObservers() {
        observeHeaderImage()
        observePostList()
    }

    private fun observePostList() = with(binding) {
        with(viewModel) {
            postList.onEach {
                postAdapter.submitList(it)
            }.launchIn(lifecycleScope)
        }
    }

    private fun observeHeaderImage() = with(binding) {
        with(viewModel) {
            headerImage.onEach {
                headerAdapter.setItems(it)
            }.launchIn(lifecycleScope)
        }
    }

    companion object {
        private const val USER_ID = "userId"
    }
}