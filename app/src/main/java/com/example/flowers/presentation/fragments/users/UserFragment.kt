package com.example.flowers.presentation.fragments.users

import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flowers.R
import com.example.flowers.databinding.FragmentUserBinding
import com.example.flowers.presentation.base.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.getViewModel


class UserFragment :
    BaseFragment<FragmentUserBinding, UserViewModel>() {

    private val userAdapter by lazy { UserAdapter(::userClick) }

    override val viewModel: UserViewModel
        get() = getViewModel()

    override fun getViewBinding(): FragmentUserBinding =
        FragmentUserBinding.inflate(layoutInflater)

    override fun initView() {
        initRecycler()
    }

    private fun initRecycler() = with(binding) {
        recyclerView.adapter = userAdapter
    }

    private fun userClick(userId: Int) = with(binding) {
        findNavController().navigate(
            R.id.action_userFragment_to_postFragment,
            bundleOf(USER_ID to userId)
        )
    }

    override fun initObservers() {
        super.initObservers()
        observeUserList()
    }

    private fun observeUserList() = with(binding) {
        with(viewModel) {
            userList.onEach {
                userAdapter.submitList(it)
            }.launchIn(lifecycleScope)
        }
    }

    companion object {
        private const val USER_ID = "userId"
    }
}