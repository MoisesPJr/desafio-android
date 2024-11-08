package com.picpay.desafio.android.feature.userList.data.source

import com.picpay.desafio.android.feature.userList.data.api.PicPayService
import com.picpay.desafio.android.feature.userList.data.db.UserDao
import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import com.picpay.desafio.android.feature.userList.domain.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: PicPayService,
    private val userDao: UserDao
) : UserDataSource {
    override suspend fun getUsersLocal(): List<UserRemote> {
        val users = arrayListOf<UserRemote>()
        try {
            users.addAll(userDao.getUsers())
        } catch (e: Exception) {

        }
        return if (users.isEmpty()) {
            withContext(Dispatchers.IO) {
                users.addAll(userApi.getUsers())
            }
            userDao.insertAll(users)
            users
        } else {
            users
        }
    }

    override suspend fun getUsers(): List<UserRemote> {
        try {
            val users = userApi.getUsers()
            userDao.insertAll(users)
            return users
        } catch (e: Exception) {
            throw e
        }
    }

}