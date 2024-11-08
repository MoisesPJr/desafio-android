package com.picpay.desafio.android.feature.userList.domain.repository

import com.picpay.desafio.android.feature.userList.data.model.UserRemote

interface UserRepository {

    suspend fun getUsers(): List<UserRemote>
    suspend fun getUsersLocal(): List<UserRemote>



}