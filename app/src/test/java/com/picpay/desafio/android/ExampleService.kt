package com.picpay.desafio.android

import com.picpay.desafio.android.feature.userList.data.api.PicPayService
import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class ExampleService(
    private val service: PicPayService
) {

    suspend fun getUsers(): List<UserRemote> {
        return withContext(Dispatchers.IO) {
            val response = service.getUsers()
            response
        }
    }
}