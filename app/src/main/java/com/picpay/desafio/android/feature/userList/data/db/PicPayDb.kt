package com.picpay.desafio.android.feature.userList.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.feature.userList.data.model.UserRemote


@Database(
    entities = [UserRemote::class],
    version = 1
)

abstract class PicPayDb : RoomDatabase() {

    abstract fun getDao(): UserDao
}