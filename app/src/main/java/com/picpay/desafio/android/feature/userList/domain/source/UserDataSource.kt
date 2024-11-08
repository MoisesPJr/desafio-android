package com.picpay.desafio.android.feature.userList.domain.source

import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import retrofit2.Call

interface UserDataSource {

    suspend fun getUsersLocal(): List<UserRemote>
    suspend fun getUsers(): List<UserRemote>
}