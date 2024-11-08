package com.picpay.desafio.android.feature.userList.data.repository

import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import com.picpay.desafio.android.feature.userList.domain.repository.UserRepository
import com.picpay.desafio.android.feature.userList.domain.source.UserDataSource
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
): UserRepository {
    override suspend fun getUsers(): List<UserRemote> {
       return dataSource.getUsers()
    }


}