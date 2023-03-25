package com.example.autohelper.data.users.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.autohelper.data.users.SignInData
import com.example.autohelper.data.users.room.entities.UserEntity

@Dao
interface UsersDao {

    @Insert
    suspend fun createUser(user: UserEntity)

    @Query("SELECT id, password FROM users WHERE login = :login")
    suspend fun findByLogin(login: String): SignInData?

    @Query("SELECT * FROM users WHERE login = :userLogin")
    suspend fun getByLogin(userLogin: String): UserEntity

}