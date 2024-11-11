package com.picpay.desafio.android.feature.userList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.databinding.FragmentUserBinding
import com.picpay.desafio.android.feature.userList.domain.model.UserDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val adp by lazy {
        UserListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.adapter = adp
            recyclerView.layoutManager = LinearLayoutManager(context)
            userListProgressBar.visibility = View.VISIBLE
            buttonTryAgain.setOnClickListener {
                viewModel.getUsers()
            }
            buttonLocalData.setOnClickListener {
                viewModel.getUsersLocal()
            }
            buttonRefresh.setOnClickListener {
                viewModel.getUsers()
            }
            viewModel.getUsersLocal()
        }

        registrationObserver()
    }

    private fun registrationObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.asFlow().collect { uiState ->
                    when (uiState) {
                        is UserUiState.Success -> setUsers(uiState.users)
                        is UserUiState.Error -> handleError()
                    }
                }
            }
        }
    }

    private fun handleError() {
        binding.layoutContacts.visibility = View.GONE
        binding.layoutError.visibility = View.VISIBLE

    }


    private fun setUsers(user: List<UserDomain>) {
        if (user.isNotEmpty()) {
            binding.layoutContacts.visibility = View.VISIBLE
            binding.layoutError.visibility = View.GONE
            binding.userListProgressBar.visibility = View.GONE
            adp.users = user
            adp.notifyDataSetChanged()
        }

    }

}
