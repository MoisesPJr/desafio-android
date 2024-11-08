package com.picpay.desafio.android.feature.userList.data.api

import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
  suspend  fun getUsers(): List<UserRemote>
}