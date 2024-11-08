package com.picpay.desafio.android

import com.picpay.desafio.android.feature.userList.data.api.PicPayService
import com.picpay.desafio.android.feature.userList.data.model.UserRemote

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example(): List<UserRemote> {
        val users = service.getUsers()

        return users
    }
}