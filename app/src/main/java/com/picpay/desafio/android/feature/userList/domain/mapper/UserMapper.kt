package com.picpay.desafio.android.feature.userList.domain.mapper

import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import com.picpay.desafio.android.feature.userList.domain.model.UserDomain

fun UserRemote.toDomain(): UserDomain {
    return UserDomain(
        img = img,
        name = name,
        id = id,
        username = username
    )
}