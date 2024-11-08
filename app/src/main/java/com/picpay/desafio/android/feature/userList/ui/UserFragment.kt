package com.picpay.desafio.android.feature.userList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.databinding.FragmentUserBinding
import com.picpay.desafio.android.feature.userList.ErrorsEnum
import com.picpay.desafio.android.feature.userList.domain.model.UserDomain
import dagger.hilt.android.AndroidEntryPoint

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
            buttonRefresh.setOnClickListener {
                viewModel.getUsers()
            }
            viewModel.getUsers()
        }
        registrationObserver()
    }

    private fun registrationObserver() {
        viewModel.user.observe(viewLifecycleOwner, userObserver)
        viewModel.error.observe(viewLifecycleOwner, errorObserver)
    }


    private val userObserver = Observer<List<UserDomain>> { users ->
        setUsers(users)
    }

    private fun handleError() {
        binding.layoutContacts.visibility = View.GONE
        binding.layoutError.visibility = View.VISIBLE

    }


    private fun setUsers(user: List<UserDomain>) {
        if(user.isNotEmpty()){
            binding.layoutContacts.visibility = View.VISIBLE
            binding.layoutError.visibility = View.GONE
            binding.userListProgressBar.visibility = View.GONE
            adp.users = user
            adp.notifyDataSetChanged()
        }

    }

    private val errorObserver = Observer<ErrorsEnum> { _ ->
        handleError()
    }
}