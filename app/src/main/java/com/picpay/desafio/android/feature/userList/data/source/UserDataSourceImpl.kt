package com.picpay.desafio.android.feature.userList.data.source

import com.picpay.desafio.android.feature.userList.data.api.PicPayService
import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import com.picpay.desafio.android.feature.userList.domain.source.UserDataSource
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: PicPayService
) : UserDataSource {
    override suspend fun getUsers(): List<UserRemote> {
        return userApi.getUsers()
    }

}