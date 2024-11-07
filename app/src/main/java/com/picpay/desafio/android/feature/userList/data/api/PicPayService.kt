package com.picpay.desafio.android.feature.userList.data.api

import com.picpay.desafio.android.feature.userList.data.model.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}