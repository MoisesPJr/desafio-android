package com.picpay.desafio.android.feature.userList.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.feature.userList.data.model.UserRemote

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
     fun getUsers() : List<UserRemote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( users: List<UserRemote>)
}