package com.picpay.desafio.android.feature.userList.domain.source

import com.picpay.desafio.android.feature.userList.data.model.UserRemote

interface UserDataSource {

    suspend fun getUsers(): List<UserRemote>
}