package com.picpay.desafio.android.feature.userList.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserRemote(
    val img: String?,
    val name: String?,
    @PrimaryKey val id: Int,
    val username: String?
)